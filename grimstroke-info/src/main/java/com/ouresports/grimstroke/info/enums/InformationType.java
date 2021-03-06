package com.ouresports.grimstroke.info.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

/**
 *
 * @author will
 * @date 2018/11/29
 */
public enum InformationType implements IEnum {
    /**
     * 资讯
     */
    NEWS("News"),
    /**
     * 视频
     */
    Video("Video"),
    /**
     * 专题
     */
    COLLECTION("InfoCollection"),
    ;

    private String type;

    InformationType(final String type) {
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
