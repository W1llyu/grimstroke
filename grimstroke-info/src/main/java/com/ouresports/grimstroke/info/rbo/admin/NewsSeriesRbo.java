package com.ouresports.grimstroke.info.rbo.admin;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *
 * @author will
 * @date 2018/12/17
 */
@Data
public class NewsSeriesRbo {
    @NotNull(message="比赛id不可为空")
    private Long seriesId;
}
