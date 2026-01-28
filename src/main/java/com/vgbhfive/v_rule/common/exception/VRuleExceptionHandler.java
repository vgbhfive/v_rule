package com.vgbhfive.v_rule.common.exception;

import com.vgbhfive.v_rule.dto.ResponseContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author vgbhfive
 * @Date 2026/1/28 13:19
 */
@RestControllerAdvice
public class VRuleExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(VRuleExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseContent handle(Exception e) {
        logger.error("[系统异常]", e);
        return ResponseContent.success(e.getMessage());
    }

}
