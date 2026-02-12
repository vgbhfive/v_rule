package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.CheckParams;
import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.common.enums.ElementTypes;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.datacategory.DataCategoryQueryParam;
import com.vgbhfive.v_rule.entity.DataCategoryEntity;
import com.vgbhfive.v_rule.service.DataCategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/26 21:27
 */
@RestController
@RequestMapping("/dataCategory")
public class DataCategoryController {

    @Resource
    private DataCategoryService dataCategoryService;

    @PostMapping("/list")
    @Log
    @Permission
    public ResponseContent queryList(@Valid @RequestBody DataCategoryQueryParam param) {
        return dataCategoryService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    @Permission
    @CheckParams(type = ElementTypes.DATA_CATEGORY)
    public ResponseContent create(@RequestBody DataCategoryEntity dataCategoryEntity) {
        return dataCategoryService.create(dataCategoryEntity, false);
    }

    @PostMapping("/update")
    @Log
    @Permission
    @CheckParams(type = ElementTypes.DATA_CATEGORY, isUpdate = true)
    public ResponseContent update(@RequestBody DataCategoryEntity dataCategoryEntity) {
        return dataCategoryService.update(dataCategoryEntity);
    }

    @PostMapping("/valid")
    @Log
    @Permission
    public ResponseContent valid(@RequestBody DataCategoryEntity dataCategoryEntity) {
        return dataCategoryService.updateValid(dataCategoryEntity.getId(), dataCategoryEntity.getIsValid());
    }

    @PostMapping("/dropdown/list")
    @Log
    @Permission
    public ResponseContent dropdownList(@RequestBody DataCategoryQueryParam param) {
        return dataCategoryService.dropdownList(param);
    }

}
