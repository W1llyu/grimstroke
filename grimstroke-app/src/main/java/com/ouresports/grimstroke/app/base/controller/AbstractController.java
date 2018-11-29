package com.ouresports.grimstroke.app.base.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author will
 * @date 2018/11/27
 */
public abstract class AbstractController {
    protected ResponseEntity render(Object obj) {
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    protected ResponseEntity render(Object obj, HttpStatus httpStatus) {
        return new ResponseEntity<>(obj, httpStatus);
    }
}
