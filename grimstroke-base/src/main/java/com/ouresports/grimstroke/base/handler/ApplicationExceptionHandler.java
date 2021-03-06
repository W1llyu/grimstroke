package com.ouresports.grimstroke.base.handler;

import com.ouresports.grimstroke.base.exception.ApplicationException;
import com.ouresports.grimstroke.base.template.ResultTemplate;
import com.ouresports.grimstroke.base.exception.ServiceException;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

import static com.ouresports.grimstroke.base.enums.ApplicationError.*;

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
        return generateResponseEntity(ResultTemplate.of(RESOURCE_NOT_FOUND));
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity applicationExceptionHandler(ApplicationException e) {
        return generateResponseEntity(e.toResult());
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity serviceExceptionHandler(ServiceException e) {
        return generateResponseEntity(ResultTemplate.of(HttpStatus.NOT_ACCEPTABLE, e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity constraintViolationExceptionHandler(ConstraintViolationException e) {
        String errorMessage = "参数错误";
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        if (!constraintViolations.isEmpty()) {
            errorMessage = constraintViolations.iterator().next().getMessage();
        }
        return generateResponseEntity(ResultTemplate.of(NOT_ACCEPTABLE.status(), NOT_ACCEPTABLE.value(), errorMessage));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String errorMessage = "参数错误";
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        if (!errors.isEmpty()) {
            errorMessage = errors.get(0).getDefaultMessage();
        }
        return generateResponseEntity(ResultTemplate.of(NOT_ACCEPTABLE.status(), NOT_ACCEPTABLE.value(), errorMessage));
    }

    protected ResponseEntity generateResponseEntity(ResultTemplate result) {
        return new ResponseEntity<>(result, result.getStatus());
    }
}
