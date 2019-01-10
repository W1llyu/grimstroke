package com.ouresports.grimstroke.lib.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 *
 * @author will
 * @date 2018/12/4
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LibServiceException extends Exception {
    private String message;
    private int code;
    @Getter
    private boolean withErrorCode;

    public LibServiceException(String message) {
        this.withErrorCode = false;
        this.message = message;
    }

    public LibServiceException(int code, String message) {
        this.withErrorCode = true;
        this.code = code;
        this.message = message;
    }
}
