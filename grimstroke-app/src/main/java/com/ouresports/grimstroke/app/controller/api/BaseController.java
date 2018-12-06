package com.ouresports.grimstroke.app.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ouresports.grimstroke.app.base.controller.AbstractController;
import com.ouresports.grimstroke.app.enums.ApplicationError;
import com.ouresports.grimstroke.app.base.exception.*;
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
    
    private static final String AUTH_HEADER_KEY = "Authorization";

    /**
     * 检查用户token
     * @throws ApplicationException
     */
    protected void authenticateUserForce() throws ApplicationException {
        String token = request.getHeader(AUTH_HEADER_KEY);
        currentUser = userService.findBy(new QueryWrapper<User>().eq("token", token));
        if (currentUser == null) {
            throw new ApplicationException(ApplicationError.TOKEN_ERROR);
        }
    }

    /**
     * 检查用户token
     */
    protected void authenticateUser() throws ApplicationException {
        if (request.getHeader(AUTH_HEADER_KEY) == null) {
            return;
        }
        authenticateUserForce();
    }
}
