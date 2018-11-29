package com.ouresports.grimstroke.core.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

/**
 *
 * @author will
 * @date 2018/11/28
 */
public enum InformationSubType implements IEnum {
    /**
     * 普通资讯
     */
    NEWS("News"),
    /**
     * 竞猜课堂
     */
    BETCOURSE("BetCourse"),
    /**
     * 专家分析类资讯
     */
    ANALYSIS("Analysis"),
    ;

    private String type;

    InformationSubType(final String type) {
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
