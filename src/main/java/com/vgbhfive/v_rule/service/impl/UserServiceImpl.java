package com.vgbhfive.v_rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.enums.UserStatus;
import com.vgbhfive.v_rule.common.exception.DataBaseException;
import com.vgbhfive.v_rule.common.utils.Md5Util;
import com.vgbhfive.v_rule.common.utils.RedisUtil;
import com.vgbhfive.v_rule.dto.PageResponse;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.user.*;
import com.vgbhfive.v_rule.entity.UserEntity;
import com.vgbhfive.v_rule.mapper.UserLineMapper;
import com.vgbhfive.v_rule.mapper.UserMapper;
import com.vgbhfive.v_rule.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author vgbhfive
 * @Date 2025/12/3 10:33
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserLineMapper userLineMapper;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public ResponseContent login(LoginReq loginReq) {
        UserEntity entity = userMapper.selectByEmail(loginReq.getEmail());
        if (Objects.nonNull(entity)) {
            if (entity.getStatus().equals(UserStatus.FROZEN.getCode())) {
                return ResponseContent.success("账户已被冻结，请联系管理员");
            }
            if (entity.getStatus().equals(UserStatus.DEFAULT_PASSWORD.getCode())) {
                return ResponseContent.success("请修改初始密码后登陆");
            }
            boolean isEqual = Md5Util.verify(loginReq.getPassword(), entity.getSalt(), entity.getPassword());
            if (isEqual) {
                String token = UUID.randomUUID().toString().replace("-", "");
                UserInfo userInfo = buildUserInfo(entity);
                redisUtil.set(Constant.REDIS_PREFIX + token, userInfo, 30, TimeUnit.MINUTES);
                return ResponseContent.success(new LoginResp(entity.getEmail(), entity.getName(), entity.getMobile(), token));
            }
        }
        return ResponseContent.success("用户名或密码错误");
    }

    private UserInfo buildUserInfo(UserEntity entity) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(entity.getId());
        userInfo.setName(entity.getName());
        userInfo.setEmail(entity.getEmail());
        userInfo.setMobile(entity.getMobile());
        userInfo.setAdmin(entity.getEmail().equals("admin"));
        userInfo.setLineNoSet(userLineMapper.userLineDetails(entity.getId()));
        return userInfo;
    }

    @Override
    public ResponseContent info(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return ResponseContent.success("未知异常");
        }
        String key = Constant.REDIS_PREFIX + token;
        UserInfo userInfo = redisUtil.getObject(key, UserInfo.class);
        if (Objects.nonNull(userInfo)) {
            return ResponseContent.success(new LoginResp(userInfo.getEmail(), userInfo.getName(), userInfo.getMobile(), token));
        }
        return ResponseContent.success("token 已失效");
    }

    @Override
    public ResponseContent register(UserEntity userEntity) {
        if (userEntity.getEmail().equals("admin")) {
            return ResponseContent.success("创建用户失败");
        }
        UserEntity entity = userMapper.selectByEmail(userEntity.getEmail());
        if (Objects.nonNull(entity)) {
            return ResponseContent.success("用户已存在");
        }
        int salt = new SecureRandom().nextInt(100000);
        String md5Password = Md5Util.md5(Constant.DEFAULT_PASSWORD + salt);
        Date now = new Date();
        userEntity.setId(null);
        userEntity.setSalt(salt);
        userEntity.setPassword(md5Password);
        userEntity.setStatus(UserStatus.DEFAULT_PASSWORD.getCode());
        userEntity.setCreateAt(now);
        userEntity.setUpdateAt(now);
        Integer insert = userMapper.insert(userEntity);
        if (insert < 1) {
            throw new DataBaseException("创建用户失败");
        }
        return ResponseContent.success("创建用户成功");
    }

    @Override
    public ResponseContent resetPassword(String email) {
        UserEntity entity = userMapper.selectByEmail(email);
        if (Objects.isNull(entity)) {
            return ResponseContent.success("邮箱不存在");
        }
        int salt = new SecureRandom().nextInt(100000);
        String md5Password = Md5Util.md5(Constant.DEFAULT_PASSWORD + salt);
        UserEntity updateEntity = new UserEntity();
        updateEntity.setEmail(email);
        updateEntity.setPassword(md5Password);
        updateEntity.setSalt(salt);
        updateEntity.setStatus(UserStatus.DEFAULT_PASSWORD.getCode());
        updateEntity.setUpdateAt(new Date());
        userMapper.updateByEmail(updateEntity);
        return ResponseContent.success("密码已重置");
    }

    @Override
    public ResponseContent freeze(String email) {
        UserEntity entity = userMapper.selectByEmail(email);
        if (Objects.isNull(entity)) {
            return ResponseContent.success("邮箱不存在");
        }
        UserEntity oldUserEntity = new UserEntity();
        oldUserEntity.setStatus(1);
        int update = userMapper.update(oldUserEntity,
                new QueryWrapper<UserEntity>().eq("email", email));
        if (update < 1) {
            return ResponseContent.success("冻结用户失败");
        }
        return ResponseContent.success("冻结用户成功");
    }

    @Override
    public ResponseContent normal(String email) {
        UserEntity entity = userMapper.selectByEmail(email);
        if (Objects.isNull(entity)) {
            return ResponseContent.success("邮箱不存在");
        }
        UserEntity oldUserEntity = new UserEntity();
        oldUserEntity.setStatus(0);
        int update = userMapper.update(oldUserEntity,
                new QueryWrapper<UserEntity>().eq("email", email));
        if (update < 1) {
            return ResponseContent.success("操作用户失败");
        }
        return ResponseContent.success("用户已恢复");
    }

    @Override
    public ResponseContent changePassword(ChangePasswordParam param) {
        UserEntity entity = userMapper.selectByEmail(param.getEmail());
        if (Objects.isNull(entity)) {
            return ResponseContent.success("用户不存在");
        }
        if (entity.getStatus() == 1) {
            return ResponseContent.success("账户已冻结");
        }

        String oldPassword = param.getOldPassword();
        String newPassword = param.getNewPassword();
        String newDupPassword = param.getNewDupPassword();
        if (!newPassword.equals(newDupPassword)) {
            return ResponseContent.success("两次输入密码不一致");
        }
        boolean isEqual = Md5Util.verify(oldPassword, entity.getSalt(), entity.getPassword());
        if (!isEqual) {
            return ResponseContent.success("原密码错误");
        }
        int salt = new SecureRandom().nextInt(100000);
        String md5Password = Md5Util.md5(newPassword + salt);
        UserEntity updateEntity = new UserEntity();
        updateEntity.setEmail(param.getEmail());
        updateEntity.setPassword(md5Password);
        updateEntity.setSalt(salt);
        updateEntity.setStatus(UserStatus.NORMAL.getCode());
        updateEntity.setUpdateAt(new Date());
        userMapper.updateByEmail(updateEntity);
        return ResponseContent.success("修改密码成功");
    }

    @Override
    public ResponseContent<UserInfo> verifyLogin(String token) {
        String key = Constant.REDIS_PREFIX + token;
        UserInfo userInfo = redisUtil.getObject(key, UserInfo.class);
        if (Objects.nonNull(userInfo)) {
            redisUtil.expire(key, 30, TimeUnit.MINUTES);
            return ResponseContent.success(userInfo);
        }
        return ResponseContent.error(100, "未查询到用户信息");
    }

    @Override
    public ResponseContent refreshToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return ResponseContent.success("未知异常");
        }
        String key = Constant.REDIS_PREFIX + token;
        UserInfo userInfo = redisUtil.getObject(key, UserInfo.class);
        redisUtil.delete(key);
        if (Objects.nonNull(userInfo)) {
            String tokenNew = UUID.randomUUID().toString().replace("-", "");
            redisUtil.set(Constant.REDIS_PREFIX + tokenNew, userInfo, 30, TimeUnit.MINUTES);
            return ResponseContent.success(new LoginResp("", "", "", token));
        }
        return ResponseContent.success("token 已失效");
    }

    @Override
    public ResponseContent list(UserQueryParam param) {
        int start = (param.getCurrPage() - 1) * param.getLimit();
        int limit = param.getLimit();

        List<UserListDto> userListDtoList = userMapper.queryList(param, start, limit);
        int totalCount = userMapper.queryTotalCount(param);

        int totalPage = (totalCount - 1) / limit + 1;
        PageResponse<UserListDto> result = new PageResponse<>(param.getCurrPage(), limit, totalCount, totalPage, userListDtoList);
        return ResponseContent.success(result);
    }

    @Override
    public ResponseContent dropdownList() {
        return ResponseContent.success(userMapper.dropdownList());
    }

}
