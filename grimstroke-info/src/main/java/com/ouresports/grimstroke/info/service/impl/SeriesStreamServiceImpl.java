package com.ouresports.grimstroke.info.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ouresports.grimstroke.base.exception.ServiceException;
import com.ouresports.grimstroke.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.base.service.UserService;
import com.ouresports.grimstroke.base.util.WrapperUtil;
import com.ouresports.grimstroke.info.dto.LiveStreamDto;
import com.ouresports.grimstroke.info.dto.SeriesLiveStreamCountDto;
import com.ouresports.grimstroke.info.entity.LiveStream;
import com.ouresports.grimstroke.info.entity.association.SeriesStream;
import com.ouresports.grimstroke.info.mapper.SeriesStreamMapper;
import com.ouresports.grimstroke.info.service.SeriesStreamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.ouresports.grimstroke.base.enums.ServiceError.SERIES_ALREADY_IN_LIVESTREAM;
import static com.ouresports.grimstroke.base.enums.ServiceError.SERIES_NOT_IN_LIVESTREAM;

/**
 *
 * @author will
 * @date 2018/12/20
 */
@Service
public class SeriesStreamServiceImpl extends BaseServiceImpl<SeriesStreamMapper, SeriesStream> implements SeriesStreamService {
    @Resource
    private UserService userService;

    @Override
    public void addSeries(LiveStream liveStream, long seriesId) throws ServiceException {
        SeriesStream seriesStream = new SeriesStream().setLiveStreamId(liveStream.getId()).setSeriesId(seriesId);
        if (count(seriesStream) > 0) {
            throw new ServiceException(SERIES_ALREADY_IN_LIVESTREAM);
        }
        findOrCreateBy(seriesStream);
    }

    @Override
    public void removeSeries(LiveStream liveStream, long seriesId) throws ServiceException {
        QueryWrapper<SeriesStream> wrapper = new QueryWrapper<>();
        SeriesStream seriesStream = new SeriesStream().setLiveStreamId(liveStream.getId()).setSeriesId(seriesId);
        WrapperUtil.appendEqualQuery(wrapper, seriesStream);
        if (count(wrapper) == 0) {
            throw new ServiceException(SERIES_NOT_IN_LIVESTREAM);
        }
        remove(wrapper);
    }

    @Override
    public List<SeriesLiveStreamCountDto> getSeriesLiveStreamCount(List<Long> seriesIds) {
        return baseMapper.selectSeriesLiveStreamCount(seriesIds);
    }

    @Override
    public List<LiveStreamDto> getLiveStreamDtosBySeries(long seriesId) {
        List<LiveStreamDto> dtos = baseMapper.selectLiveStreamDtosBySeries(seriesId);
        userService.includeUsers(dtos);
        return dtos;
    }
}
