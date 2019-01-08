package com.ouresports.grimstroke.im.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.im.GrimstrokeImApplicationTest;
import com.ouresports.grimstroke.im.dto.MatchSeriesDto;
import com.ouresports.grimstroke.im.entity.MatchSeries;
import org.junit.Test;

import javax.annotation.Resource;

import static com.ouresports.grimstroke.im.enums.MatchSeriesStatus.Finished;
import static com.ouresports.grimstroke.im.enums.MatchSeriesStatus.NotStartYet;
import static com.ouresports.grimstroke.im.enums.MatchSeriesStatus.Ongoing;

/**
 * Created by will on 2018/12/21.
 */
public class MatchSeriesServiceTest extends GrimstrokeImApplicationTest {
    @Resource
    private MatchSeriesService matchSeriesService;

    @Test
    public void testGetDtos() {
        Page<MatchSeriesDto> page = new Page<>(1, 10);
        QueryWrapper<MatchSeries> wrapper = new QueryWrapper<MatchSeries>()
                .eq("`match_series`.`status`", Ongoing)
                .eq("`match_series`.`start_time`", 1800000)
                .or().eq("`match_series`.`status`", NotStartYet).lt("`match_series`.`start_time`", 1800000)
                .or().eq("`match_series`.`status`", Finished).ge("`match_series`.`start_time`", 60000)
                .orderByDesc("`match_series`.`created_at`");
        IPage<MatchSeriesDto> dtos = matchSeriesService.getDtos(page, wrapper);
    }
}
