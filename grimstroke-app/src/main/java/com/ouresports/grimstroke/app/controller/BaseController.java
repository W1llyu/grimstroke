package com.ouresports.grimstroke.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ouresports.grimstroke.app.base.controller.AbstractController;
import com.ouresports.grimstroke.app.enums.ApplicationError;
import com.ouresports.grimstroke.app.exception.*;
import com.ouresports.grimstroke.core.entity.User;
import com.ouresports.grimstroke.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author will
 * @date 2018/11/28
 */
public abstract class BaseController extends AbstractController {
    @Autowired
    protected HttpServletRequest request;
    @Resource
    protected UserService userService;
    protected User currentUser;

    protected void authenticateUser() throws ApplicationException {
        String token = request.getHeader("Authorization");
        currentUser = userService.findBy(new QueryWrapper<User>().eq("token", token));
        if (currentUser == null) {
            throw new ApplicationException(ApplicationError.TOKEN_ERROR);
        }
    }
}
