package com.ouresports.grimstroke.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.core.dto.InformationDto;
import com.ouresports.grimstroke.core.entity.InfoCollection;
import com.ouresports.grimstroke.core.mapper.InfoCollectionMapper;
import com.ouresports.grimstroke.core.service.InfoCollectionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/29
 */
@Service
public class InfoCollectionServiceImpl extends BaseServiceImpl<InfoCollectionMapper, InfoCollection> implements InfoCollectionService {
    @Override
    public List<InformationDto> getInformationDtos(Wrapper<InformationDto> wrapper) {
        return baseMapper.selectInformationDtos(null, wrapper);
    }

    @Override
    public IPage<InformationDto> getInformationDtoPages(IPage<InformationDto> page, Wrapper<InformationDto> wrapper) {
        List<InformationDto> list = baseMapper.selectInformationDtos(page, wrapper);
        page.setRecords(list);
        return page;
    }

    @Override
    public IPage<InformationDto> getInformationDtosOfCol(IPage<InformationDto> page,
                                                   long infoCollectionId,
                                                   Wrapper<InformationDto> wrapper) {
        List<InformationDto> list = baseMapper.selectInformationDtosOfCol(page, infoCollectionId, wrapper);
        page.setRecords(list);
        return page;
    }
}
