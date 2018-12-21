package com.ouresports.grimstroke.im.enums;

import com.ouresports.grimstroke.base.enums.ServiceError;

/**
 *
 * @author will
 * @date 2018/12/21
 */
public enum ImServiceError implements ServiceError {
    /**
     * 错误Enum
     */
    MATCHSERIES_STATUS_INVALID(5101, "比赛状态不合法"),
    USER_BANED_IN_CHATROOM(5041, "用户已被禁言"),
    CHATROOM_NOT_OPEN(5043, "聊天室未开启"),
    CHATROOM_CLOSED(5044, "聊天室已关闭"),
    ;

    private int value;
    private String message;

    ImServiceError(int value, String message) {
        this.value = value;
        this.message = message;
    }

    @Override
    public int value() {
        return value;
    }

    @Override
    public String message() {
        return message;
    }
}
