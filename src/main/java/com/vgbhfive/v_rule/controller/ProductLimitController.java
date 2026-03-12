package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.CheckParams;
import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.common.enums.ElementTypes;
import com.vgbhfive.v_rule.common.enums.PermissionType;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductInterestEntity;
import com.vgbhfive.v_rule.entity.ProductLimitEntity;
import com.vgbhfive.v_rule.service.ProductLimitService;
import com.vgbhfive.v_rule.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 额度
 * @Author vgbhfive
 * @Date 2025/11/28 11:33
 */
@RestController
@RequestMapping("/product/limit")
public class ProductLimitController {

    @Resource
    private ProductLimitService productLimitService;
    @Resource
    private ProductService productService;

    @PostMapping("/list")
    @Log
    @Permission(sign = "product_limit_manage", checkPermission = true, type = PermissionType.PAGE)
    public ResponseContent queryList(@Valid @RequestBody ProductQueryParam param) {
        return productLimitService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    @Permission(sign = "product_limit_manage_create", checkPermission = true, type = PermissionType.BUTTON)
    @CheckParams(type = ElementTypes.PRODUCT)
    public ResponseContent create(@RequestBody ProductLimitEntity productLimitEntity) {
        return productLimitService.create(productLimitEntity, false);
    }

    @PostMapping("/update")
    @Log
    @Permission(sign = "product_limit_manage_update", checkPermission = true, type = PermissionType.BUTTON)
    @CheckParams(type = ElementTypes.PRODUCT, isUpdate = true)
    public ResponseContent update(@RequestBody ProductLimitEntity productLimitEntity) {
        return productLimitService.update(productLimitEntity);
    }

    @PostMapping("/valid")
    @Log
    @Permission(sign = "product_limit_manage_valid", checkPermission = true, type = PermissionType.BUTTON)
    public ResponseContent valid(@RequestBody ProductInterestEntity productInterestEntity) {
        return productService.updateValid(productInterestEntity.getId(), productInterestEntity.getIsValid());
    }

}
