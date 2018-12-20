package com.ouresports.grimstroke.info.entity.association;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author will
 * @date 2018/12/20
 */
@TableName("series_streams")
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain=true)
public class SeriesStream extends BaseEntity {
    private Long liveStreamId;
    private Long seriesId;
}
