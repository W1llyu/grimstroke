package com.ouresports.grimstroke.info.mapper;

import com.ouresports.grimstroke.base.mapper.BaseMapper;
import com.ouresports.grimstroke.info.dto.LiveStreamDto;
import com.ouresports.grimstroke.info.dto.SeriesLiveStreamCountDto;
import com.ouresports.grimstroke.info.entity.association.SeriesStream;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/12/20
 */
@Component
public interface SeriesStreamMapper extends BaseMapper<SeriesStream> {
    List<SeriesLiveStreamCountDto> selectSeriesLiveStreamCount(List<Long> seriesIds);

    List<LiveStreamDto> selectLiveStreamDtosBySeries(@Param("series_id") long seriesId);
}
