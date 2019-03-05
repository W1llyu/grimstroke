package com.ouresports.grimstroke.base.mapper;

import com.ouresports.grimstroke.base.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.ouresports.grimstroke.base.entity.Employee;
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
