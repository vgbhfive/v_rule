package com.vgbhfive.v_rule.service.impl;

import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.enums.UserStatus;
import com.vgbhfive.v_rule.common.exception.DataBaseException;
import com.vgbhfive.v_rule.common.utils.Md5Util;
import com.vgbhfive.v_rule.common.utils.RedisUtil;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.user.ChangePasswordParam;
import com.vgbhfive.v_rule.dto.user.LoginReq;
import com.vgbhfive.v_rule.dto.user.LoginResp;
import com.vgbhfive.v_rule.dto.user.UserInfo;
import com.vgbhfive.v_rule.entity.UserEntity;
import com.vgbhfive.v_rule.mapper.UserMapper;
import com.vgbhfive.v_rule.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.Date;
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
    @Resource
    private RedisUtil redisUtil;

    @Override
    public ResponseContent login(LoginReq loginReq) {
        UserEntity entity = userMapper.selectByEmail(loginReq.getEmail());
        if (Objects.nonNull(entity)) {
            if (entity.getStatus().equals(UserStatus.FROZEN.getCode())) {
                return ResponseContent.error(500, "账户已被冻结，请联系管理员");
            }
            if (entity.getStatus().equals(UserStatus.DEFAULT_PASSWORD.getCode())) {
                return ResponseContent.error(500, "请修改初始密码后登陆");
            }
            boolean isEqual = Md5Util.verify(loginReq.getPassword(), entity.getSalt(), entity.getPassword());
            if (isEqual) {
                String token = UUID.randomUUID().toString().replace("-", "");
                UserInfo userInfo = buildUserInfo(entity);
                redisUtil.set(Constant.REDIS_PREFIX + token, userInfo, 30, TimeUnit.MINUTES);
                return ResponseContent.success(new LoginResp(entity.getEmail(), entity.getName(), entity.getMobile(), token));
            }
        }
        return ResponseContent.error(500, "用户名或密码错误");
    }

    private UserInfo buildUserInfo(UserEntity entity) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(entity.getId());
        userInfo.setName(entity.getName());
        userInfo.setEmail(entity.getEmail());
        userInfo.setMobile(entity.getMobile());
        userInfo.setAdmin(entity.getEmail().equals("admin"));
        return userInfo;
    }

    @Override
    public ResponseContent info(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return ResponseContent.error("未知异常");
        }
        String key = Constant.REDIS_PREFIX + token;
        UserInfo userInfo = redisUtil.getObject(key, UserInfo.class);
        if (Objects.nonNull(userInfo)) {
            return ResponseContent.success(new LoginResp(userInfo.getEmail(), userInfo.getName(), userInfo.getMobile(), token));
        }
        return ResponseContent.error("token已失效");
    }

    @Override
    public ResponseContent register(UserEntity userEntity) {
        if (userEntity.getEmail().equals("admin")) {
            return ResponseContent.error(100, "创建用户失败");
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
        return ResponseContent.success();
    }

    @Override
    public ResponseContent resetPassword(String email) {
        UserEntity entity = userMapper.selectByEmail(email);
        if (Objects.isNull(entity)) {
            return ResponseContent.error(500, "邮箱不存在");
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
    public ResponseContent changePassword(ChangePasswordParam param) {
        UserEntity entity = userMapper.selectByEmail(param.getEmail());
        if (Objects.isNull(entity)) {
            return ResponseContent.error(500, "用户不存在");
        }
        if (entity.getStatus() == 1) {
            return ResponseContent.error(500, "账户已冻结");
        }
        if (entity.getStatus() != 2) {
            return ResponseContent.error(500, "初始密码已修改");
        }

        String oldPassword = param.getOldPassword();
        String newPassword = param.getNewPassword();
        String newDupPassword = param.getNewDupPassword();
        if (!newPassword.equals(newDupPassword)) {
            return ResponseContent.error(500, "两次输入密码不一致");
        }
        boolean isEqual = Md5Util.verify(oldPassword, entity.getSalt(), entity.getPassword());
        if (!isEqual) {
            return ResponseContent.error(500, "原密码错误");
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
    public ResponseContent verifyLogin(String token) {
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
            return ResponseContent.error("未知异常");
        }
        String key = Constant.REDIS_PREFIX + token;
        UserInfo userInfo = redisUtil.getObject(key, UserInfo.class);
        redisUtil.delete(key);
        if (Objects.nonNull(userInfo)) {
            String tokenNew = UUID.randomUUID().toString().replace("-", "");
            redisUtil.set(Constant.REDIS_PREFIX + tokenNew, userInfo, 30, TimeUnit.MINUTES);
            return ResponseContent.success(new LoginResp("", "", "", token));
        }
        return ResponseContent.error("token已失效");
    }

}
