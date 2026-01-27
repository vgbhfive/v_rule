package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.line.LineQueryParam;
import com.vgbhfive.v_rule.entity.LineEntity;
import com.vgbhfive.v_rule.service.LineService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/24 23:12
 */
@RestController
@RequestMapping("/line")
public class LineController {

    @Resource
    private LineService lineService;

    @GetMapping("/list")
    @Log
    @Permission
    public ResponseContent queryList(@RequestBody LineQueryParam param) {
        return lineService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    @Permission
    public ResponseContent create(@Valid @RequestBody LineEntity lineEntity) {
        return lineService.create(lineEntity, false);
    }

    @PostMapping("/update")
    @Log
    @Permission
    public ResponseContent update(@Valid @RequestBody LineEntity lineEntity) {
        return lineService.update(lineEntity);
    }

    @GetMapping("/dropdown/list")
    @Log
    @Permission
    public ResponseContent dropdownList() {
        return lineService.dropdownList();
    }

}
