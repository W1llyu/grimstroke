package com.ouresports.grimstroke.info.service.impl;

import com.ouresports.grimstroke.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.info.entity.User;
import com.ouresports.grimstroke.info.mapper.UserMapper;
import com.ouresports.grimstroke.info.service.UserService;
import org.springframework.stereotype.Service;

/**
 *
 * @author will
 * @date 2018/11/22
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
}
