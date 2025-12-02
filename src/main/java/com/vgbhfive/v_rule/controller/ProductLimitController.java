package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.service.ProductDynamicLimitService;
import com.vgbhfive.v_rule.service.ProductLimitService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 11:33
 */
@RestController
@RequestMapping("/product/limit")
public class ProductLimitController {

    @Resource
    private ProductLimitService productLimitService;
    @Resource
    private ProductDynamicLimitService productDynamicLimitService;

    @PostMapping("/list")
    @Log
    public ResponseContent queryList(@Valid @RequestBody ProductQueryParam param) {
        return productLimitService.queryList(param);
    }

    @PostMapping("/dynamic/list")
    @Log
    public ResponseContent queryDynamicList(@Valid @RequestBody ProductQueryParam param) {
        return productDynamicLimitService.queryList(param);
    }

}
