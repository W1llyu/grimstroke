package com.ouresports.grimstroke.info.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

/**
 *
 * @author will
 * @date 2018/12/19
 */
public enum ExternLivePlatform implements IEnum {
    /**
     * 直播平台
     */
    Twitch("Twitch"),
    Douyu("Douyu"),
    Huya("Huya")
    ;

    private String platform;

    ExternLivePlatform(final String platform) {
        this.platform = platform;
    }

    @Override
    public Serializable getValue() {
        return platform;
    }

    @Override
    public String toString() {
        return platform;
    }
}
