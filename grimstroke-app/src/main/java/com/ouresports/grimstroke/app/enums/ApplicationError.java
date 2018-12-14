package com.ouresports.grimstroke.app.enums;

import org.springframework.http.HttpStatus;

/**
 *
 * @author will
 * @date 2018/11/28
 */
public enum ApplicationError {
    /**
     * errors
     */
    NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE, 1001, "请求参数错误"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, 1002, "资源不存在"),
    SERVICE_ERROR(HttpStatus.BAD_REQUEST, 1010, "调用第三方服务错误"),
    TOKEN_ERROR(HttpStatus.UNAUTHORIZED, 2001, "token错误"),
    ;

    private final HttpStatus status;
    private final int value;
    private final String message;

    ApplicationError(HttpStatus status, int value, String message) {
        this.status = status;
        this.value = value;
        this.message = message;
    }

    public HttpStatus status() {
        return this.status;
    }

    public int value() {
        return this.value;
    }

    public String message() {
        return this.message;
    }
}
