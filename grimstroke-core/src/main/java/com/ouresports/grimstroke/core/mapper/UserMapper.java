package com.ouresports.grimstroke.core.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ouresports.grimstroke.core.base.mapper.BaseMapper;
import com.ouresports.grimstroke.core.entity.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author will
 * @date 2018/11/22
 */
@Component
@DS("arcwarden")
public interface UserMapper extends BaseMapper<User> {
}
