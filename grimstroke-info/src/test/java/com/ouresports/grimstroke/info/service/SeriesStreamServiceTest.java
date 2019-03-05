package com.ouresports.grimstroke.info.service;

import com.google.common.collect.Lists;
import com.ouresports.grimstroke.info.GrimstrokeInfoApplicationTest;
import com.ouresports.grimstroke.info.dto.LiveStreamDto;
import com.ouresports.grimstroke.info.dto.SeriesLiveStreamCountDto;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by will on 2018/12/20.
 */
public class SeriesStreamServiceTest extends GrimstrokeInfoApplicationTest {
    @Resource
    private SeriesStreamService seriesStreamService;

    @Test
    public void testGetSeriesLiveStreamCount() {
        List<SeriesLiveStreamCountDto> dtos = seriesStreamService.getSeriesLiveStreamCount(Lists.newArrayList(1L,2L,3L));
    }

    @Test
    public void testGetLiveStreamDtosBySeries() {
        List<LiveStreamDto> dtos = seriesStreamService.getLiveStreamDtosBySeries(1L);
    }
}
