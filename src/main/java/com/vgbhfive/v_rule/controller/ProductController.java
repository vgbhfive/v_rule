package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductInterestEntity;
import com.vgbhfive.v_rule.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author vgbhfive
 * @Date 2026/1/30 16:48
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @PostMapping("/valid")
    @Log
    @Permission
    public ResponseContent valid(@RequestBody ProductInterestEntity productInterestEntity) {
        return productService.updateValid(productInterestEntity.getId(), productInterestEntity.getIsValid());
    }

    @PostMapping("/dropdown/list")
    @Log
    @Permission
    public ResponseContent dropdownList(@RequestBody ProductQueryParam param) {
        return productService.dropdownList(param);
    }

}
