package com.vgbhfive.v_rule.common.aop;

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
import java.lang.reflect.Method;
import java.util.Objects;

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
            String token = request.getHeader("token");

            if (Objects.isNull(token)) {
                return ResponseContent.error(100, "token不能为空");
            }
            ResponseContent resp = userService.verifyLogin(token);
            if (!resp.getStatus().equals(200)) {
                return ResponseContent.error(100, "登陆失效");
            }
        }

        //调用执行目标方法
        Object obj = point.proceed();
        return obj;
    }

}
