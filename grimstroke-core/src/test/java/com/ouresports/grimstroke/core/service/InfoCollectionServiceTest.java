package com.ouresports.grimstroke.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.core.GrimstrokeCoreApplicationTest;
import com.ouresports.grimstroke.core.dto.InformationDto;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by will on 2018/11/29.
 */
public class InfoCollectionServiceTest extends GrimstrokeCoreApplicationTest {
    @Resource
    private InfoCollectionService infoCollectionService;

    @Test
    public void testGetInformation() {
        QueryWrapper<InformationDto> wrapper = new QueryWrapper<InformationDto>().eq("game_id", 3).eq("enabled", true);
        IPage<InformationDto> informationDtos = infoCollectionService.getInformationDtos(new Page<>(1, 2), 1, wrapper);
    }
}
