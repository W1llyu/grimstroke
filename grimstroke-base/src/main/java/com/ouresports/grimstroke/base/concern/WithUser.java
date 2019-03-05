package com.ouresports.grimstroke.base.concern;

import com.ouresports.grimstroke.base.entity.User;

/**
 *
 * @author will
 * @date 2018/12/20
 */
public interface WithUser {
    Long getUserId();
    void setUserId(Long userId);
    User getUser();
    void setUser(User user);
}
