package com.vgbhfive.controller;

import com.vgbhfive.common.aop.Log;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.product.ProductQueryParam;
import com.vgbhfive.service.ProductPeriodService;
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
@RequestMapping("/product/period")
public class ProductPeriodController {

    @Resource
    private ProductPeriodService productPeriodService;

    @PostMapping("/list")
    @Log
    public ResponseContent queryList(@Valid @RequestBody ProductQueryParam param) {
        return productPeriodService.queryList(param);
    }

}
