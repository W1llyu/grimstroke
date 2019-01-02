package com.ouresports.grimstroke.info.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

/**
 *
 * @author will
 * @date 2019/1/2
 */
public enum StreamTemplate implements IEnum {
    /**
     * 直播流清晰度
     */
    LLD("lld"),
    LSD("lsd"),
    LHD("lhd"),
    LUD("lud");

    private String id;

    StreamTemplate(final String id) {
        this.id = id;
    }

    @Override
    public Serializable getValue() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }
}
