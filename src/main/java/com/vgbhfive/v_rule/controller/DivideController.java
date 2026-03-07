package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.CheckParams;
import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.common.enums.ElementTypes;
import com.vgbhfive.v_rule.common.enums.PermissionType;
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

    @PostMapping("/list")
    @Log
    @Permission(sign = "divide_manage", checkPermission = true, type = PermissionType.PAGE)
    public ResponseContent queryList(@Valid @RequestBody DivideQueryParam param) {
        return divideService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    @Permission(sign = "divide_manage_create", checkPermission = true, type = PermissionType.BUTTON)
    @CheckParams(type = ElementTypes.DIVIDE)
    public ResponseContent create(@RequestBody DivideEntity divideEntity) {
        return divideService.create(divideEntity, false);
    }

    @PostMapping("/update")
    @Log
    @Permission(sign = "divide_manage_update", checkPermission = true, type = PermissionType.BUTTON)
    @CheckParams(type = ElementTypes.DIVIDE, isUpdate = true)
    public ResponseContent update(@RequestBody DivideEntity divideEntity) {
        return divideService.update(divideEntity);
    }

    @GetMapping("/detail/{id}")
    @Log
    @Permission(sign = "divide_manage_detail", checkPermission = true, type = PermissionType.BUTTON)
    public ResponseContent detail(@PathVariable("id") Integer id) {
        return divideService.detail(id);
    }

    @PostMapping("/valid")
    @Log
    @Permission(sign = "divide_manage_valid", checkPermission = true, type = PermissionType.BUTTON)
    public ResponseContent valid(@RequestBody DivideEntity divideEntity) {
        return divideService.updateValid(divideEntity.getId(), divideEntity.getIsValid());
    }

    @GetMapping("/dropdown/list")
    @Log
    @Permission
    public ResponseContent dropdownList() {
        return divideService.dropdownList();
    }

}
