package com.ouresports.grimstroke.app.handler;

import com.ouresports.grimstroke.app.base.template.ResultTemplate;
import com.ouresports.grimstroke.app.base.exception.*;
import com.ouresports.grimstroke.core.exception.ServiceException;
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

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity serviceExceptionHandler(ServiceException e) {
        return generateResponseEntity(ResultTemplate.of(HttpStatus.NOT_ACCEPTABLE, e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity constraintViolationExceptionHandler(ConstraintViolationException e) {
        String errorMessage = "参数错误";
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        if (!constraintViolations.isEmpty()) {
            StringBuilder msgBuilder = new StringBuilder();
            for(ConstraintViolation constraintViolation :constraintViolations){
                msgBuilder.append(constraintViolation.getMessage()).append(",");
            }
            errorMessage = msgBuilder.toString();
            if(errorMessage.length()>1){
                errorMessage = errorMessage.substring(0,errorMessage.length()-1);
            }
        }
        return generateResponseEntity(ResultTemplate.of(HttpStatus.NOT_ACCEPTABLE, 1001, errorMessage));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String errorMessage = "参数错误";
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        if (!errors.isEmpty()) {
            errorMessage = errors.get(0).getDefaultMessage();
        }
        return generateResponseEntity(ResultTemplate.of(HttpStatus.NOT_ACCEPTABLE, 1001, errorMessage));
    }

    private ResponseEntity generateResponseEntity(ResultTemplate result) {
        return new ResponseEntity<>(result, result.getStatus());
    }
}
