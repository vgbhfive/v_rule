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

    public static <Object> ResponseContent<Object> success() {
        return success(null);
    }

    public static <T> ResponseContent<T> success(T object) {
        ResponseContent<T> result = new ResponseContent<>();
        result.setStatus(200);
        result.setMsg("success");
        result.setData(object);
        return result;
    }

    public static <T> ResponseContent<T> success(String msg, T object, Class<T> dataClazz) {
        ResponseContent<T> result = new ResponseContent<>();
        result.setStatus(200);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }

    public static <Object> ResponseContent<Object> error() {
        ResponseContent<Object> res = new ResponseContent<Object>();
        res.setStatus(199);
        return res;
    }

    public static <Object> ResponseContent<Object> error(String msg) {
        ResponseContent<Object> res = new ResponseContent<Object>();
        res.setStatus(199);
        res.setMsg(msg);
        return res;
    }

    public static <Object> ResponseContent<Object> error(Integer code, String msg) {
        ResponseContent<Object> res = new ResponseContent<Object>();
        res.setStatus(code);
        res.setMsg(msg);
        return res;
    }

}
