package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
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

    @GetMapping("/list")
    @Log
    @Permission
    public ResponseContent queryList(@Valid @RequestBody ProductQueryParam param) {
        return productCustomService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    @Permission
    public ResponseContent create(@RequestBody ProductEntity productEntity) {
        return productCustomService.create(productEntity, false);
    }

    @GetMapping("/detail/{id}")
    @Log
    @Permission
    public ResponseContent create(@PathVariable("id") Integer id) {
        return productCustomService.detail(id);
    }

    @PostMapping("/update")
    @Log
    @Permission
    public ResponseContent update(@RequestBody ProductEntity productEntity) {
        return productCustomService.update(productEntity);
    }

}
