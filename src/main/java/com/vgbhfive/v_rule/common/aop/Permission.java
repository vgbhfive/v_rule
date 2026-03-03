package com.vgbhfive.v_rule.common.aop;

import com.vgbhfive.v_rule.common.enums.PermissionType;

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

    String sign() default "N/A";

    // 是否需要校验使用权限
    boolean checkPermission() default true;

    PermissionType type() default PermissionType.PAGE;

}
