package com.vgbhfive.common.exception;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/25 23:38
 */
public class DataBaseException extends RuntimeException{

    public DataBaseException(String message) {
        super(message);
    }

    public DataBaseException() {
        super();
    }

}
