package com.ouresports.grimstroke.info.enums;

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
    News("News"),
    /**
     * 竞猜课堂
     */
    BetCourse("BetCourse"),
    /**
     * 专家分析类资讯
     */
    Analysis("Analysis"),
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

    public static InformationSubType getValueByCode(String type){
        for(InformationSubType subType: InformationSubType.values()){
            if(type.equals(subType.getValue())){
                return subType;
            }
        }
        return  null;
    }
}
