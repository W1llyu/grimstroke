package com.ouresports.grimstroke.core.service;

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
        IPage<InformationDto> informationDtos = infoCollectionService.getInformationDtoOfCol(new Page<>(1, 10), 1);
    }
}
