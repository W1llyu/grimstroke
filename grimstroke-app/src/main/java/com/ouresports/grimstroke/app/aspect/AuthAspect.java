package com.ouresports.grimstroke.app.aspect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ouresports.grimstroke.app.base.exception.ApplicationException;
import com.ouresports.grimstroke.app.enums.ApplicationError;
import com.ouresports.grimstroke.core.entity.Employee;
import com.ouresports.grimstroke.core.entity.User;
import com.ouresports.grimstroke.core.service.EmployeeService;
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

import static com.ouresports.grimstroke.app.aspect.AspectConstant.*;

/**
 *
 * @author will
 * @date 2018/12/8
 */
@Aspect
@Component
@Order(2)
public class AuthAspect {
    @Resource
    private EmployeeService employeeService;

    @Pointcut("(@within(com.ouresports.grimstroke.app.base.annotation.AuthToken) || @annotation(com.ouresports.grimstroke.app.base.annotation.AuthToken)) " +
            "&& execution(public * com.ouresports.grimstroke.app.controller.api.*.*(..))")
    public void userAuthAction() {}

    @Before("userAuthAction()")
    public void authenticateUser(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = getRequest();
        User user = (User) request.getAttribute(USER_REQUEST_KEY);
        if (user == null) {
            throw new ApplicationException(ApplicationError.TOKEN_ERROR);
        }
    }

    @Pointcut("(@within(com.ouresports.grimstroke.app.base.annotation.AuthToken) || @annotation(com.ouresports.grimstroke.app.base.annotation.AuthToken)) " +
            "&& (execution(public * com.ouresports.grimstroke.app.base.controller.BaseCrudController.*(..)) || execution(public * com.ouresports.grimstroke.app.controller.admin.*.*(..)))")
    public void adminAuthAction() {}

    @Before("adminAuthAction()")
    public void authenticateAdmin(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = getRequest();
        String token = request.getHeader(AUTH_HEADER_KEY);
        Employee employee = employeeService.findBy(new QueryWrapper<Employee>().eq("token", token));
        if (employee == null) {
            throw new ApplicationException(ApplicationError.TOKEN_ERROR);
        }
        request.setAttribute(EMPLOYEE_REQUEST_KEY, employee);
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }
}
