package com.vgbhfive.v_rule.common.aop;

import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.enums.PermissionType;
import com.vgbhfive.v_rule.common.utils.RequestHolder;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.user.UserInfo;
import com.vgbhfive.v_rule.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/12/5 0:28
 */
@Aspect
@Component
@Order(2)
public class PermissionAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Resource
    private UserService userService;

    @Pointcut("execution(* com.vgbhfive.v_rule.controller.*.*(..)) && @annotation(com.vgbhfive.v_rule.common.aop.Permission)")
    public void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Permission permission = method.getAnnotation(Permission.class);
        if (Objects.nonNull(permission)) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            HttpServletResponse response = servletRequestAttributes.getResponse();
            String token = request.getHeader("token");

            if (Objects.isNull(token)) {
                response.setStatus(401);
                return ResponseContent.success("token 不能为空");
            }
            ResponseContent<UserInfo> resp = userService.verifyLogin(token);
            if (!resp.getStatus().equals(200)) {
                response.setStatus(401);
                return ResponseContent.success("登陆失效");
            }

            UserInfo userInfo = resp.getData();
            logger.info("userInfo: " + userInfo);
            if (Objects.isNull(RequestHolder.get())) {
                RequestHolder.set(new HashMap<>());
            }
            // TODO
            if (permission.checkPermission() && !userInfo.isAdmin()) {
                List<String> operatePermissionSet = permission.type().equals(PermissionType.PAGE) ?
                        userInfo.getPagePermission() : userInfo.getButtonPermission();
                if (Objects.isNull(operatePermissionSet) ||
                        !operatePermissionSet.contains(permission.sign())) {
                    logger.info("permission set:{}, permission:{}", operatePermissionSet, permission);
                    return ResponseContent.error("你没有此权限，请联系管理员");
                }
            }

            if (!userInfo.isAdmin()) {
                RequestHolder.get().put(Constant.LINE_PERMISSION_SET, userInfo.getLineNoSet());
            }
            RequestHolder.get().put(Constant.USER_INFO, userInfo);
        }

        //调用执行目标方法
        Object obj = point.proceed();
        // 清除 ThreadLocal 中权限相关内容
        RequestHolder.remove();
        return obj;
    }

}
