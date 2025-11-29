package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.datasource.DataSourceQueryParam;
import com.vgbhfive.v_rule.entity.DataSourceEntity;
import com.vgbhfive.v_rule.service.DataSourceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author vgbhfive
 * @Date 2025/11/26 12:12
 */
@RestController
@RequestMapping("/data/source")
public class DataSourceController {

    @Resource
    private DataSourceService dataSourceService;

    @PostMapping("/list")
    @Log
    public ResponseContent queryList(@Valid @RequestBody DataSourceQueryParam param) {
        return dataSourceService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    public ResponseContent create(@RequestBody DataSourceEntity dataSourceEntity) {
        return dataSourceService.create(dataSourceEntity, false);
    }

    @PostMapping("/update")
    @Log
    public ResponseContent update(@RequestBody DataSourceEntity dataSourceEntity) {
        return dataSourceService.update(dataSourceEntity);
    }

}
