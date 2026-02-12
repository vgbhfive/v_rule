package com.vgbhfive.v_rule.common.aop;

import com.vgbhfive.v_rule.common.enums.ElementTypes;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author vgbhfive
 * @Date 2026/2/12 17:02
 */
@Documented
@Retention(RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Valid {

    ElementTypes type();

}
