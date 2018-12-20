package com.ouresports.grimstroke.info.dto;

import lombok.Data;

/**
 *
 * @author will
 * @date 2018/12/20
 */
@Data
public class SeriesLiveStreamCountDto {
    private Long seriesId;
    private Integer externLiveStreamCount;
    private Integer ouresportsLiveStreamCount;
}
