package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.CheckParams;
import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.common.enums.ElementTypes;
import com.vgbhfive.v_rule.common.enums.PermissionType;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductInterestEntity;
import com.vgbhfive.v_rule.entity.ProductPeriodEntity;
import com.vgbhfive.v_rule.service.ProductPeriodService;
import com.vgbhfive.v_rule.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 账期
 * @Author vgbhfive
 * @Date 2025/11/28 11:33
 */
@RestController
@RequestMapping("/product/period")
public class ProductPeriodController {

    @Resource
    private ProductPeriodService productPeriodService;
    @Resource
    private ProductService productService;

    @PostMapping("/list")
    @Log
    @Permission(sign = "product_period_manage", checkPermission = true, type = PermissionType.PAGE)
    public ResponseContent queryList(@Valid @RequestBody ProductQueryParam param) {
        return productPeriodService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    @Permission(sign = "product_period_manage_create", checkPermission = true, type = PermissionType.BUTTON)
    @CheckParams(type = ElementTypes.PRODUCT)
    public ResponseContent create(@RequestBody ProductPeriodEntity productPeriodEntity) {
        return productPeriodService.create(productPeriodEntity, false);
    }

    @PostMapping("/update")
    @Log
    @Permission(sign = "product_period_manage_update", checkPermission = true, type = PermissionType.BUTTON)
    @CheckParams(type = ElementTypes.PRODUCT, isUpdate = true)
    public ResponseContent update(@RequestBody ProductPeriodEntity productPeriodEntity) {
        return productPeriodService.update(productPeriodEntity);
    }

    @PostMapping("/valid")
    @Log
    @Permission(sign = "product_period_manage_valid", checkPermission = true, type = PermissionType.BUTTON)
    public ResponseContent valid(@RequestBody ProductInterestEntity productInterestEntity) {
        return productService.updateValid(productInterestEntity.getId(), productInterestEntity.getIsValid());
    }

}
