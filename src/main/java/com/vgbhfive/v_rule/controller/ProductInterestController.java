package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductInterestEntity;
import com.vgbhfive.v_rule.service.ProductInterestService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/27 23:02
 */
@RestController
@RequestMapping("/product/interest")
public class ProductInterestController {

    @Resource
    private ProductInterestService productInterestService;

    @GetMapping("/list")
    @Log
    @Permission
    public ResponseContent queryList(@Valid @RequestBody ProductQueryParam param) {
        return productInterestService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    @Permission
    public ResponseContent create(@RequestBody ProductInterestEntity productInterestEntity) {
        return productInterestService.create(productInterestEntity, false);
    }

    @PostMapping("/update")
    @Log
    @Permission
    public ResponseContent update(@RequestBody ProductInterestEntity productInterestEntity) {
        return productInterestService.update(productInterestEntity);
    }

}
