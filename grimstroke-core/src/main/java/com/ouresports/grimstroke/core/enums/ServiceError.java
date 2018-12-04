package com.ouresports.grimstroke.core.enums;

/**
 *
 * @author will
 * @date 2018/12/4
 */
public enum ServiceError {
    /**
     * 错误Enum
     */
    CANNOT_COMMENT_SELF(2001, "不能回复自己"),
    ALREADY_LIKED(2011, "不能重复点赞"),
    NOT_LIKED_YET(2012, "还没有点过赞")
    ;

    private final int value;
    private final String message;

    ServiceError(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int value() {
        return value;
    }

    public String message() {
        return message;
    }
}
