package com.vgbhfive.controller;

import com.vgbhfive.common.aop.Log;
import com.vgbhfive.dto.ResponseContent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/26 21:10
 */
@RestController
@RequestMapping
public class HelloController {

    private static Boolean flag = true;

    @GetMapping("/alive")
    @Log
    public ResponseContent alive(HttpServletResponse servletResponse) {
        if (flag) {
            return ResponseContent.success();
        }
        servletResponse.setStatus(500);
        return ResponseContent.error();
    }

    @GetMapping("/stop")
    public ResponseContent stop(HttpServletResponse servletResponse) {
        flag = false;
        return ResponseContent.success("stop server!");
    }

    @GetMapping("/start")
    public ResponseContent start(HttpServletResponse servletResponse) {
        flag = true;
        return ResponseContent.success("start server!");
    }

}
