package com.ouresports.grimstroke.im.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

/**
 *
 * @author will
 * @date 2018/12/21
 */
public enum MatchSeriesStatus implements IEnum {
    /**
     * 比赛的状态
     */
    Failed(-3, "failed"),
    RoundChanged(-2, "round_changed"),
    Canceled(-1, "canceled"),
    NotStartYet(0, "not_start_yet"),
    Ongoing(1, "ongoing"),
    Finished(2, "finished")
    ;

    private int code;
    private String status;

    MatchSeriesStatus(int code, String status) {
        this.code = code;
        this.status = status;
    }

    @Override
    public Serializable getValue() {
        return code;
    }

    @Override
    public String toString() {
        return status;
    }
}
