package com.vgbhfive.controller;

import com.vgbhfive.common.aop.Log;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.datacategory.DataCategoryQueryParam;
import com.vgbhfive.entity.DataCategoryEntity;
import com.vgbhfive.service.DataCategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/26 21:27
 */
@RestController
@RequestMapping("/data/category")
public class DataCategoryController {

    @Resource
    private DataCategoryService dataCategoryService;

    @PostMapping("/list")
    @Log
    public ResponseContent queryList(@Valid @RequestBody DataCategoryQueryParam param) {
        return dataCategoryService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    public ResponseContent create(@RequestBody DataCategoryEntity dataCategoryEntity) {
        return dataCategoryService.create(dataCategoryEntity, false);
    }

    @PostMapping("/update")
    @Log
    public ResponseContent update(@RequestBody DataCategoryEntity dataCategoryEntity) {
        return dataCategoryService.update(dataCategoryEntity);
    }

}
