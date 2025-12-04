package com.vgbhfive.v_rule.common.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/12/5 0:28
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface Permission {
}
