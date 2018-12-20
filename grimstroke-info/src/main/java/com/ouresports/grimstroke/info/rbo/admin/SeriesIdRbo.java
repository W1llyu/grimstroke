package com.ouresports.grimstroke.info.rbo.admin;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *
 * @author will
 * @date 2018/12/20
 */
@Data
public class SeriesIdRbo {
    @NotNull(message="比赛id不可为空")
    private Long seriesId;
}
