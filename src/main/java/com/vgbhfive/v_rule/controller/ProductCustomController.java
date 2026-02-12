package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.CheckParams;
import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.common.enums.ElementTypes;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductEntity;
import com.vgbhfive.v_rule.service.ProductCustomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 11:33
 */
@RestController
@RequestMapping("/product/custom")
public class ProductCustomController {

    @Resource
    private ProductCustomService productCustomService;

    @PostMapping("/list")
    @Log
    @Permission
    public ResponseContent queryList(@Valid @RequestBody ProductQueryParam param) {
        return productCustomService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    @Permission
    @CheckParams(type = ElementTypes.PRODUCT)
    public ResponseContent create(@RequestBody ProductEntity productEntity) {
        return productCustomService.create(productEntity, false);
    }

    @GetMapping("/detail/{id}")
    @Log
    @Permission
    public ResponseContent detail(@PathVariable("id") Integer id) {
        return productCustomService.detail(id);
    }

    @PostMapping("/update")
    @Log
    @Permission
    @CheckParams(type = ElementTypes.PRODUCT, isUpdate = true)
    public ResponseContent update(@RequestBody ProductEntity productEntity) {
        return productCustomService.update(productEntity);
    }

}
