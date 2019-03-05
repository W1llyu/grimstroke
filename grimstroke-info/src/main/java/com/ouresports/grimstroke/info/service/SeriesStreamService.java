package com.ouresports.grimstroke.info.service;

import com.ouresports.grimstroke.base.exception.ServiceException;
import com.ouresports.grimstroke.base.service.Service;
import com.ouresports.grimstroke.info.dto.LiveStreamDto;
import com.ouresports.grimstroke.info.dto.SeriesLiveStreamCountDto;
import com.ouresports.grimstroke.info.entity.LiveStream;
import com.ouresports.grimstroke.info.entity.association.SeriesStream;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/12/20
 */
public interface SeriesStreamService extends Service<SeriesStream> {
    void addSeries(LiveStream liveStream, long seriesId) throws ServiceException;

    void removeSeries(LiveStream liveStream, long series) throws ServiceException;

    List<SeriesLiveStreamCountDto> getSeriesLiveStreamCount(List<Long> seriesIds);

    List<LiveStreamDto> getLiveStreamDtosBySeries(long seriesId);
}
