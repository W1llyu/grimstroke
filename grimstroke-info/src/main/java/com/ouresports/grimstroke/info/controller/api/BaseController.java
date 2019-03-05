package com.ouresports.grimstroke.info.controller.api;

import com.ouresports.grimstroke.base.controller.AbstractController;
import com.ouresports.grimstroke.base.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

import static com.ouresports.grimstroke.base.aspect.AspectConstant.USER_REQUEST_KEY;

/**
 *
 * @author will
 * @date 2018/11/28
 */
public abstract class BaseController extends AbstractController {
    @Autowired
    protected HttpServletRequest request;

    protected User getCurrentUser() {
        return (User) request.getAttribute(USER_REQUEST_KEY);
    }
}
