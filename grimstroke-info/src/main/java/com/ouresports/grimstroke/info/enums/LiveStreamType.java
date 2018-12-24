package com.ouresports.grimstroke.info.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

/**
 *
 * @author will
 * @date 2018/12/19
 */
public enum LiveStreamType implements IEnum {
    /**
     * 即最快视频流
     */
    Official("Official"),
    /**
     * 即解说
     */
    Anchor("Anchor");

    private String type;

    LiveStreamType(final String type) {
        this.type = type;
    }

    @Override
    public Serializable getValue() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
