package com.vgbhfive.controller;

import com.vgbhfive.common.aop.Log;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.datasource.DataSourceQueryParam;
import com.vgbhfive.entity.DataSourceEntity;
import com.vgbhfive.service.DataSourceService;
import lombok.Value;
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
@RequestMapping("/datasource")
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
        return ResponseContent.success();
    }

    @PostMapping("/update")
    @Log
    public ResponseContent update(@RequestBody DataSourceEntity dataSourceEntity) {
        return ResponseContent.success();
    }

}
