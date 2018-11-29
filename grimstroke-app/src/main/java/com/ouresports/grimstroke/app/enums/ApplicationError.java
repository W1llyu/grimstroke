package com.ouresports.grimstroke.app.enums;

import org.springframework.http.HttpStatus;

/**
 *
 * @author will
 * @date 2018/11/28
 */
public enum ApplicationError {
    /**
     * token错误
     */
    TOKEN_ERROR(HttpStatus.UNAUTHORIZED, 2000, "token错误"),
    ;

    private final HttpStatus status;
    private final int value;
    private final String message;

    private ApplicationError(HttpStatus status, int value, String message) {
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
