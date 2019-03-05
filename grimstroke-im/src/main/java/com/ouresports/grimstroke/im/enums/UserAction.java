package com.ouresports.grimstroke.im.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

/**
 *
 * @author will
 * @date 2018/12/22
 */
public enum UserAction implements IEnum {
    /**
     * 用户动作
     */
    JoinRoom("JoinRoom"),
    LeaveRoom("LeaveRoom")
    ;

    private String action;

    UserAction(String action) {
        this.action = action;
    }

    @Override
    public Serializable getValue() {
        return action;
    }

    @Override
    public String toString() {
        return action;
    }
}
