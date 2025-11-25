package com.vgbhfive.controller;

import com.vgbhfive.common.aop.Log;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.line.LineQueryParam;
import com.vgbhfive.entity.LineEntity;
import com.vgbhfive.service.LineService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/24 23:12
 */
@RestController
@RequestMapping("/line")
public class LineController {

    @Resource
    private LineService lineService;

    @PostMapping("/list")
    @Log
    public ResponseContent queryList(@RequestBody LineQueryParam param) {
        return lineService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    public ResponseContent create(@Valid @RequestBody LineEntity lineEntity, HttpServletRequest request) {
        return lineService.create(lineEntity, request, false);
    }

    @PostMapping("/update")
    @Log
    public ResponseContent update(@Valid @RequestBody LineEntity lineEntity, HttpServletRequest request) {
        return lineService.update(lineEntity, request);
    }

}
