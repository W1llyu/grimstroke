package com.ouresports.grimstroke.base.enums;

/**
 *
 * @author will
 * @date 2018/12/4
 */
public enum ServiceError {
    /**
     * 错误Enum
     */
    CANNOT_COMMENT_SELF(3001, "不能回复自己"),
    ALREADY_LIKED(3011, "不能重复点赞"),
    NOT_LIKED_YET(3012, "还没有点过赞"),
    ALREADY_NOTIFIED(3016, "已经增加过通知了"),
    INFORMATION_ALREADY_IN_COLLECTION(3050, "信息流已经在专栏中了"),
    INFORMATION_NOT_IN_COLLECTION(3051, "信息流还不在专栏中")
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
