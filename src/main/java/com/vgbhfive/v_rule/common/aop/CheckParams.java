package com.vgbhfive.v_rule.common.aop;

import com.vgbhfive.v_rule.common.enums.ElementTypes;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author vgbhfive
 * @Date 2026/2/12 16:54
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface CheckParams {

    ElementTypes type();

    boolean isUpdate() default false;

}
