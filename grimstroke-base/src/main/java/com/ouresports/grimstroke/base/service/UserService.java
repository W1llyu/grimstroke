package com.ouresports.grimstroke.base.service;

import com.ouresports.grimstroke.base.concern.WithUser;
import com.ouresports.grimstroke.base.entity.User;

import java.util.Collection;

/**
 *
 * @author will
 * @date 2018/11/22
 */
public interface UserService extends Service<User> {
    <T extends WithUser> void includeUsers(Collection<T> cols);

    void includeUser(WithUser userable);

    User getUserByToken(String token);
}
