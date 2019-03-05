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
    Twitch("twitch", false),
    Douyu("douyu", true),
    Huya("huya", false),
    Huomao("huomao", true),
    Youtube("youtube", false)
    ;

    private String platform;
    private Boolean transcoding;

    ExternLivePlatform(final String platform, final Boolean transcoding) {
        this.platform = platform;
        this.transcoding = transcoding;
    }

    public Serializable getValue() {
        return platform;
    }

    public Boolean getTranscoding() {
        return transcoding;
    }

    @Override
    public String toString() {
        return platform;
    }
}
