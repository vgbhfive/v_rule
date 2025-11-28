package com.vgbhfive.controller;

import com.vgbhfive.common.aop.Log;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.product.ProductQueryParam;
import com.vgbhfive.entity.ProductInterestEntity;
import com.vgbhfive.service.ProductInterestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/27 23:02
 */
@RestController
@RequestMapping("/product/interest")
public class ProductInterestController {

    @Resource
    private ProductInterestService productInterestService;

    @PostMapping("/list")
    @Log
    public ResponseContent queryList(@Valid @RequestBody ProductQueryParam param) {
        return productInterestService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    public ResponseContent create(@RequestBody ProductInterestEntity productInterestEntity) {
        return productInterestService.create(productInterestEntity, false);
    }

    @PostMapping("/update")
    @Log
    public ResponseContent update(@RequestBody ProductInterestEntity productInterestEntity) {
        return productInterestService.update(productInterestEntity);
    }

}
