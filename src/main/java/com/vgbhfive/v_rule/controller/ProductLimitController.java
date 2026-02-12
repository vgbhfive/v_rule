package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.CheckParams;
import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.common.enums.ElementTypes;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductLimitEntity;
import com.vgbhfive.v_rule.service.ProductLimitService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/list")
    @Log
    @Permission
    public ResponseContent queryList(@Valid @RequestBody ProductQueryParam param) {
        return productLimitService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    @Permission
    @CheckParams(type = ElementTypes.PRODUCT)
    public ResponseContent create(@RequestBody ProductLimitEntity productLimitEntity) {
        return productLimitService.create(productLimitEntity, false);
    }

    @PostMapping("/update")
    @Log
    @Permission
    @CheckParams(type = ElementTypes.PRODUCT, isUpdate = true)
    public ResponseContent update(@RequestBody ProductLimitEntity productLimitEntity) {
        return productLimitService.update(productLimitEntity);
    }

}
