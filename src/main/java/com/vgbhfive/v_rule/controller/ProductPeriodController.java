package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.service.ProductPeriodService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 11:33
 */
@RestController
@RequestMapping("/product/period")
public class ProductPeriodController {

    @Resource
    private ProductPeriodService productPeriodService;

    @GetMapping("/list")
    @Log
    public ResponseContent queryList(@Valid @RequestBody ProductQueryParam param) {
        return productPeriodService.queryList(param);
    }

}
