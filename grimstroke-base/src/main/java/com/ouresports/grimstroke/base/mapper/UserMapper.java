package com.ouresports.grimstroke.base.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ouresports.grimstroke.base.entity.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author will
 * @date 2018/11/22
 */
@Component(value="infoUserMapper")
@DS("arcwarden")
public interface UserMapper extends BaseMapper<User> {
}
