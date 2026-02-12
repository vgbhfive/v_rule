package com.vgbhfive.v_rule.common.aop;

import com.vgbhfive.v_rule.common.factory.ValidServiceFactory;
import com.vgbhfive.v_rule.dto.ResponseContent;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author vgbhfive
 * @Date 2026/2/12 16:54
 */
@Aspect
@Component
@Order(3)
public class CheckParamsAspect {

    private static final Logger logger = LoggerFactory.getLogger(CheckParamsAspect.class);

    @Pointcut("@annotation(com.vgbhfive.v_rule.common.aop.CheckParams)")
    public void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        CheckParams checkParams = method.getAnnotation(CheckParams.class);

        // 获取所有参数
        Object[] args = point.getArgs();
        Object body = null;
        if (args.length > 0) {
            body = args[0];
        }

        ValidServiceFactory.getHandler(checkParams.type()).checkParams(body, checkParams.isUpdate());

        //调用执行目标方法
        Object obj = point.proceed();
        return obj;
    }

}
