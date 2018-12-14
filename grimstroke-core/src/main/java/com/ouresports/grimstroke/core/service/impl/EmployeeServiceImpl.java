package com.ouresports.grimstroke.core.service.impl;

import com.ouresports.grimstroke.core.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.core.entity.Employee;
import com.ouresports.grimstroke.core.mapper.EmployeeMapper;
import com.ouresports.grimstroke.core.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 *
 * @author will
 * @date 2018/12/8
 */
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
