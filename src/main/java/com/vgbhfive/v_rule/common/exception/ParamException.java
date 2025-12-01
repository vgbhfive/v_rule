package com.vgbhfive.v_rule.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/12/1 23:34
 */
@Getter
@Setter
@AllArgsConstructor
public class ParamException extends RuntimeException {

    private Object data ;

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, Object data) {
        super(message);
        this.data = data;
    }


    public ParamException() {
        super();
    }

}
