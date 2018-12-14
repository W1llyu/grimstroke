package com.ouresports.grimstroke.app.aspect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ouresports.grimstroke.core.entity.User;
import com.ouresports.grimstroke.core.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ouresports.grimstroke.app.aspect.AspectConstant.AUTH_HEADER_KEY;
import static com.ouresports.grimstroke.app.aspect.AspectConstant.USER_REQUEST_KEY;

/**
 *
 * @author will
 * @date 2018/12/8
 */
@Aspect
@Component
@Order(1)
public class LoadCurrentAspect {
    @Resource
    private UserService userService;

    @Pointcut("execution(public * com.ouresports.grimstroke.app.controller.api.*.*(..))")
    public void loadCurrentUser() {}

    @Before("loadCurrentUser()")
    public void loadCurrentUser(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = getRequest();
        String token = request.getHeader(AUTH_HEADER_KEY);
        if (token == null) {
            return;
        }
        User user = userService.findBy(new QueryWrapper<User>().eq("token", token));
        request.setAttribute(USER_REQUEST_KEY, user);
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }
}
