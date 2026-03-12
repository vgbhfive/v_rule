package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.CheckParams;
import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.common.enums.ElementTypes;
import com.vgbhfive.v_rule.common.enums.PermissionType;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductInterestEntity;
import com.vgbhfive.v_rule.service.ProductInterestService;
import com.vgbhfive.v_rule.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 利率
 * @author: vgbhfive
 * @date: 2025/11/27 23:02
 */
@RestController
@RequestMapping("/product/interest")
public class ProductInterestController {

    @Resource
    private ProductInterestService productInterestService;
    @Resource
    private ProductService productService;

    @PostMapping("/list")
    @Log
    @Permission(sign = "product_interest_manage", checkPermission = true, type = PermissionType.PAGE)
    public ResponseContent queryList(@Valid @RequestBody ProductQueryParam param) {
        return productInterestService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    @Permission(sign = "product_interest_manage_create", checkPermission = true, type = PermissionType.BUTTON)
    @CheckParams(type = ElementTypes.PRODUCT)
    public ResponseContent create(@RequestBody ProductInterestEntity productInterestEntity) {
        return productInterestService.create(productInterestEntity, false);
    }

    @PostMapping("/update")
    @Log
    @Permission(sign = "product_interest_manage_update", checkPermission = true, type = PermissionType.BUTTON)
    @CheckParams(type = ElementTypes.PRODUCT, isUpdate = true)
    public ResponseContent update(@RequestBody ProductInterestEntity productInterestEntity) {
        return productInterestService.update(productInterestEntity);
    }

    @PostMapping("/valid")
    @Log
    @Permission(sign = "product_interest_manage_valid", checkPermission = true, type = PermissionType.BUTTON)
    public ResponseContent valid(@RequestBody ProductInterestEntity productInterestEntity) {
        return productService.updateValid(productInterestEntity.getId(), productInterestEntity.getIsValid());
    }

}
