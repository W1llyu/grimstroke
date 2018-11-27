package com.ouresports.grimstroke.app.handler;

import com.ouresports.grimstroke.app.base.dto.ApiResult;
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
        return generateResponseEntity(ApiResult.notFound());
    }

    private ResponseEntity generateResponseEntity(ApiResult result) {
        return new ResponseEntity<>(result, result.getStatus());
    }
}
