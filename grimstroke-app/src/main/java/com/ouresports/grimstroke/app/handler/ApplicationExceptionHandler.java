package com.ouresports.grimstroke.app.handler;

import com.ouresports.grimstroke.app.base.template.ResultTemplate;
import com.ouresports.grimstroke.app.exception.*;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author will
 * @date 2018/11/27
 */
@ControllerAdvice
public class ApplicationExceptionHandler {
    /**
     * 资源不存在错误
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFoundExceptionHandler(NotFoundException e) {
        return generateResponseEntity(ResultTemplate.notFound());
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity applicationExceptionHandler(ApplicationException e) {
        return generateResponseEntity(e.toResult());
    }

    private ResponseEntity generateResponseEntity(ResultTemplate result) {
        return new ResponseEntity<>(result, result.getStatus());
    }
}
