package com.ouresports.grimstroke.app.controller.api;

import com.ouresports.grimstroke.app.base.controller.AbstractController;
import com.ouresports.grimstroke.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

import static com.ouresports.grimstroke.app.aspect.AspectConstant.USER_REQUEST_KEY;

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
