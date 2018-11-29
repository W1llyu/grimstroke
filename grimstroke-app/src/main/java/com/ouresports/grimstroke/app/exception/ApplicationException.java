package com.ouresports.grimstroke.app.exception;

import com.ouresports.grimstroke.app.base.template.ResultTemplate;
import com.ouresports.grimstroke.app.enums.ApplicationError;
import org.springframework.http.HttpStatus;

/**
 *
 * @author will
 * @date 2018/11/28
 */
public class ApplicationException extends Exception {
    private int code;
    private String message;
    private HttpStatus status;

    public ApplicationException(HttpStatus status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public ApplicationException(ApplicationError error) {
        this(error.status(), error.value(), error.message());
    }

    public ResultTemplate toResult() {
        return ResultTemplate.of(status, code, message);
    }
}
