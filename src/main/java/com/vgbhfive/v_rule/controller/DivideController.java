package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.divide.DivideQueryParam;
import com.vgbhfive.v_rule.entity.DivideEntity;
import com.vgbhfive.v_rule.service.DivideService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list")
    @Log
    @Permission
    public ResponseContent queryList(@Valid @RequestBody DivideQueryParam param) {
        return divideService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    @Permission
    public ResponseContent create(@RequestBody DivideEntity divideEntity) {
        return divideService.create(divideEntity, false);
    }

    @GetMapping("/detail/{id}")
    @Log
    @Permission
    public ResponseContent detail(@PathVariable("id") Integer id) {
        return divideService.detail(id);
    }

    @PostMapping("/update")
    @Log
    @Permission
    public ResponseContent update(@RequestBody DivideEntity divideEntity) {
        return divideService.update(divideEntity);
    }

    @GetMapping("/dropdown/list")
    @Log
    @Permission
    public ResponseContent dropdownList() {
        return divideService.dropdownList();
    }

}
