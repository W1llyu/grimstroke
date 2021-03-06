package com.ouresports.grimstroke.info.enums;

import com.ouresports.grimstroke.base.enums.ServiceError;

/**
 *
 * @author will
 * @date 2018/12/4
 */
public enum InfoServiceError implements ServiceError {
    /**
     * 错误Enum
     */
    CANNOT_COMMENT_SELF(3001, "不能回复自己"),
    ALREADY_LIKED(3011, "不能重复点赞"),
    NOT_LIKED_YET(3012, "还没有点过赞"),
    ALREADY_NOTIFIED(3016, "已经增加过通知了"),
    SERIES_ALREADY_IN_NEWS(3041, "比赛已经和资讯关联了"),
    SERIES_NOT_IN_NEWS(3042, "比赛还未和资讯关联"),
    TEAM_ALREADY_IN_NEWS(3043, "队伍已经和资讯关联了"),
    TEAM_NOT_IN_NEWS(3044, "队伍还未和资讯关联"),
    INFORMATION_ALREADY_IN_COLLECTION(3051, "信息流已经在专栏中了"),
    INFORMATION_NOT_IN_COLLECTION(3052, "信息流还不在专栏中"),
    SERIES_ALREADY_IN_LIVESTREAM(3061, "比赛已经和直播流绑定了"),
    SERIES_NOT_IN_LIVESTREAM(3062, "比赛还没和直播流绑定")
    ;

    private int value;
    private String message;

    InfoServiceError(int value, String message) {
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
