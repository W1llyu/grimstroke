package com.ouresports.grimstroke.base.service.impl;

import com.ouresports.grimstroke.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.base.entity.Employee;
import com.ouresports.grimstroke.base.mapper.EmployeeMapper;
import com.ouresports.grimstroke.base.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 *
 * @author will
 * @date 2018/12/8
 */
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
