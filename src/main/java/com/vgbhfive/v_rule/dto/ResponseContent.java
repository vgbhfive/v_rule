package com.vgbhfive.v_rule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/23 23:08
 */
@Getter
@Setter
@AllArgsConstructor
public class ResponseContent<T> {

    private Integer status;

    private String reqId;

    private String msg;

    private T data;

    public ResponseContent() {
        this.reqId = UUID.randomUUID().toString().replace("-", "");
    }

    public static ResponseContent success() {
        return success(null);
    }

    public static ResponseContent success(Object object) {
        ResponseContent<Object> result = new ResponseContent<>();
        result.setStatus(200);
        result.setMsg("success");
        result.setData(object);
        return result;
    }

    public static ResponseContent error() {
        ResponseContent res = new ResponseContent();
        res.setStatus(199);
        return res;
    }

    public static ResponseContent error(String msg) {
        ResponseContent res = new ResponseContent();
        res.setStatus(199);
        res.setMsg(msg);
        return res;
    }

    public static ResponseContent error(Integer code, String msg) {
        ResponseContent res = new ResponseContent();
        res.setStatus(code);
        res.setMsg(msg);
        return res;
    }

}
