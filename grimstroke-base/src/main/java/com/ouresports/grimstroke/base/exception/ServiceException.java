package com.ouresports.grimstroke.base.exception;

import com.ouresports.grimstroke.base.enums.ServiceError;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author will
 * @date 2018/12/4
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceException extends Exception {
    private int code;
    private String message;

    public ServiceException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceException(ServiceError error) {
        this.code = error.value();
        this.message = error.message();
    }
}
