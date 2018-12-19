package com.ouresports.grimstroke.im.service.impl;

import com.ouresports.grimstroke.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.im.entity.User;
import com.ouresports.grimstroke.im.mapper.UserMapper;
import com.ouresports.grimstroke.im.service.UserService;
import org.springframework.stereotype.Service;

/**
 *
 * @author will
 * @date 2018/12/18
 */
@Service(value="imUserService")
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
}
