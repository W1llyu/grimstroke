package com.ouresports.grimstroke.im.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ouresports.grimstroke.base.mapper.BaseMapper;
import com.ouresports.grimstroke.im.entity.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author will
 * @date 2018/12/18
 */
@Component(value="imUserMapper")
@DS("arcwarden")
public interface UserMapper extends BaseMapper<User> {
}
