package com.ouresports.grimstroke.info.mapper;

import com.ouresports.grimstroke.base.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.ouresports.grimstroke.info.entity.User;
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
