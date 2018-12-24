package com.ouresports.grimstroke.lib.livestream.enums;

import java.io.Serializable;

/**
 *
 * @author will
 * @date 2018/12/19
 */
public enum ExternLivePlatform {
    /**
     * 直播平台
     */
    Twitch("twitch"),
    Douyu("douyu"),
    Huya("huya")
    ;

    private String platform;

    ExternLivePlatform(final String platform) {
        this.platform = platform;
    }

    public Serializable getValue() {
        return platform;
    }

    public String toString() {
        return platform;
    }
}
