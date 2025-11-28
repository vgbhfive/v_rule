package com.vgbhfive.controller;

import com.vgbhfive.common.aop.Log;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.divide.DivideQueryParam;
import com.vgbhfive.entity.DivideEntity;
import com.vgbhfive.service.DivideService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 12:24
 */
@RestController
@RequestMapping("/divide")
public class DivideController {

    @Resource
    private DivideService divideService;

    @PostMapping("/list")
    @Log
    public ResponseContent queryList(@Valid @RequestBody DivideQueryParam param) {
        return divideService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    public ResponseContent create(@RequestBody DivideEntity divideEntity) {
        return divideService.create(divideEntity, false);
    }

    @PostMapping("/update")
    @Log
    public ResponseContent update(@RequestBody DivideEntity divideEntity) {
        return divideService.update(divideEntity);
    }

}
