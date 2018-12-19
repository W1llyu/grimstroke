package com.ouresports.grimstroke.im.entity;

import com.ouresports.grimstroke.base.entity.BaseEntity;
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
    private Integer startTime;
}
