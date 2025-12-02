package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.service.ProductDynamicPeriodService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author vgbhfive
 * @Date 2025/12/2 17:06
 */
@RestController
@RequestMapping("/product/dynamic/period")
public class ProductDynamicPeriodController {

    @Resource
    private ProductDynamicPeriodService productDynamicPeriodService;

    @PostMapping("/list")
    @Log
    public ResponseContent queryDynamicList(@Valid @RequestBody ProductQueryParam param) {
        return productDynamicPeriodService.queryList(param);
    }

}
