package com.ouresports.grimstroke.im.vo.api;

import com.ouresports.grimstroke.base.entity.User;
import com.ouresports.grimstroke.im.enums.UserAction;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author will
 * @date 2018/12/22
 */
@Data
@Accessors(chain=true)
public class UserActionVo {
    private UserAction action;
    private UserVo user;

    public UserActionVo setUser(User user) {
        if (user != null) {
            this.user = (UserVo) new UserVo().convertFor(user);
        }
        return this;
    }
}
