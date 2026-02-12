package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.CheckParams;
import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.common.enums.ElementTypes;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.datasource.DataSourceQueryParam;
import com.vgbhfive.v_rule.entity.DataSourceEntity;
import com.vgbhfive.v_rule.service.DataSourceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author vgbhfive
 * @Date 2025/11/26 12:12
 */
@RestController
@RequestMapping("/dataSource")
public class DataSourceController {

    @Resource
    private DataSourceService dataSourceService;

    @PostMapping("/list")
    @Log
    @Permission
    public ResponseContent queryList(@Valid @RequestBody DataSourceQueryParam param) {
        return dataSourceService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    @Permission
    @CheckParams(type = ElementTypes.DATA_SOURCE)
    public ResponseContent create(@RequestBody DataSourceEntity dataSourceEntity) {
        return dataSourceService.create(dataSourceEntity, false);
    }

    @PostMapping("/update")
    @Log
    @Permission
    public ResponseContent update(@RequestBody DataSourceEntity dataSourceEntity) {
        return dataSourceService.update(dataSourceEntity);
    }

    @PostMapping("/valid")
    @Log
    @Permission
    public ResponseContent valid(@RequestBody DataSourceEntity dataSourceEntity) {
        return dataSourceService.updateValid(dataSourceEntity.getId(), dataSourceEntity.getIsValid());
    }

    @PostMapping("/dropdown/list")
    @Log
    @Permission
    public ResponseContent dropdownList(@RequestBody DataSourceQueryParam param) {
        return dataSourceService.dropdownList(param);
    }

}
