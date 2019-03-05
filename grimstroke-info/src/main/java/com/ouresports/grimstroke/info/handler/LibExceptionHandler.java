package com.ouresports.grimstroke.info.handler;

import com.ouresports.grimstroke.base.handler.ApplicationExceptionHandler;
import com.ouresports.grimstroke.base.template.ResultTemplate;
import com.ouresports.grimstroke.lib.exception.LibServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.ouresports.grimstroke.base.enums.ApplicationError.*;

/**
 *
 * @author will
 * @date 2018/11/27
 */
@ControllerAdvice
public class LibExceptionHandler extends ApplicationExceptionHandler {
    @ExceptionHandler(LibServiceException.class)
    public ResponseEntity libServiceExceptionHandler(LibServiceException e) {
        if (e.getMessage() == null) {
            return generateResponseEntity(ResultTemplate.of(SERVICE_ERROR.status(), SERVICE_ERROR.value(), String.format("%s;%s", SERVICE_ERROR.message(), e.getMessage())));
        } else {
            return generateResponseEntity(ResultTemplate.of(SERVICE_ERROR.status(), e.isWithErrorCode() ? e.getCode() : SERVICE_ERROR.value(), e.getMessage()));
        }
    }
}
