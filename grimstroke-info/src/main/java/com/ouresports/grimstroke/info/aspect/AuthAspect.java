package com.ouresports.grimstroke.info.aspect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ouresports.grimstroke.base.exception.ApplicationException;
import com.ouresports.grimstroke.base.enums.ApplicationError;
import com.ouresports.grimstroke.info.entity.Employee;
import com.ouresports.grimstroke.info.entity.User;
import com.ouresports.grimstroke.info.service.EmployeeService;
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

import static com.ouresports.grimstroke.info.aspect.AspectConstant.*;

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

    @Pointcut("(@within(com.ouresports.grimstroke.base.annotation.AuthToken) || @annotation(com.ouresports.grimstroke.base.annotation.AuthToken)) " +
            "&& execution(public * com.ouresports.grimstroke.info.controller.api.*.*(..))")
    public void userAuthAction() {}

    @Before("userAuthAction()")
    public void authenticateUser(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = getRequest();
        User user = (User) request.getAttribute(USER_REQUEST_KEY);
        if (user == null) {
            throw new ApplicationException(ApplicationError.TOKEN_ERROR);
        }
    }

    @Pointcut("(@within(com.ouresports.grimstroke.base.annotation.AuthToken) || @annotation(com.ouresports.grimstroke.base.annotation.AuthToken)) " +
            "&& (execution(public * com.ouresports.grimstroke.base.controller.BaseCrudController.*(..)) || execution(public * com.ouresports.grimstroke.info.controller.admin.*.*(..)))")
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
