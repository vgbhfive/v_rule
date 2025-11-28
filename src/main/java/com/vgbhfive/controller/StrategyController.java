package com.vgbhfive.controller;

import com.vgbhfive.common.aop.Log;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.strategy.StrategyQueryParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 12:24
 */
@RestController
@RequestMapping("/strategy")
public class StrategyController {

    @PostMapping("/list")
    @Log
    public ResponseContent queryList(@Valid @RequestBody StrategyQueryParam param) {
        return ResponseContent.success();
    }

}
