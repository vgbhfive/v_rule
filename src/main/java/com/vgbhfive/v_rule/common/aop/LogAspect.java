package com.vgbhfive.v_rule.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/23 22:56
 */
@Aspect
@Component
@Order(1)
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.vgbhfive.v_rule.common.aop.Log)")
    public void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        // 目标方法执行前的逻辑
        processInputArg(point.getArgs());
        //调用执行目标方法
        Object obj = point.proceed();
        processOutPutObj(obj, start, point);
        return obj;
    }

    /**
     * 处理返回对象，可以加一些额外需要处理的逻辑
     * @param obj 函数返回的对象
     * @param start 函数开始执行的时间
     */
    private void processOutPutObj(Object obj, long start, ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        List<String> paramNameList = Arrays.asList(signature.getParameterNames());
        List<Object> paramList = Arrays.asList(pjp.getArgs());
    }

    /**
     * 处理输入参数
     * @param args 入参列表
     */
    private void processInputArg(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof Map) {
                Map paramVO = (Map) arg;
                logger.info("input params = {}", paramVO.toString());
            } else if (arg instanceof String) {
                logger.info("input params = {}", arg);
            }
        }
    }

}
