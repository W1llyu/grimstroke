package com.ouresports.grimstroke.lib.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author will
 * @date 2018/12/4
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LibServiceException extends Exception {
    private String message;

    public LibServiceException(String message) {
        this.message = message;
    }
}
