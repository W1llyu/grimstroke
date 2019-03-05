package com.ouresports.grimstroke.im.entity;

import com.ouresports.grimstroke.base.entity.BaseEntity;
import com.ouresports.grimstroke.im.enums.MatchSeriesStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author will
 * @date 2018/12/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MatchSeries extends BaseEntity {
    private Integer gameId;
    private Integer startTime;
    private MatchSeriesStatus status;
}
