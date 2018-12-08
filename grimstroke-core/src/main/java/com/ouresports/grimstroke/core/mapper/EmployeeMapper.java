package com.ouresports.grimstroke.core.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ouresports.grimstroke.core.base.mapper.BaseMapper;
import com.ouresports.grimstroke.core.entity.Employee;
import org.springframework.stereotype.Component;

/**
 *
 * @author will
 * @date 2018/12/8
 */
@Component
@DS("arcwarden")
public interface EmployeeMapper extends BaseMapper<Employee> {
}
