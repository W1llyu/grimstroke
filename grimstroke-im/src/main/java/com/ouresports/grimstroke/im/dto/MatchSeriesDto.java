package com.ouresports.grimstroke.im.dto;

import com.ouresports.grimstroke.im.enums.MatchSeriesStatus;
import lombok.Data;

/**
 *
 * @author will
 * @date 2019/1/7
 */
@Data
public class MatchSeriesDto {
    private Long id;
    private String leftTag;
    private String rightTag;
    private String leagueName;
    private Long startTime;
    private Integer gameId;
    private MatchSeriesStatus status;
}
