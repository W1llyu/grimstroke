package com.ouresports.grimstroke.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.core.dto.InfoCollectionDto;
import com.ouresports.grimstroke.core.dto.InformationDto;
import com.ouresports.grimstroke.core.entity.InfoCollection;
import com.ouresports.grimstroke.core.mapper.InfoCollectionMapper;
import com.ouresports.grimstroke.core.service.InfoCollectionService;
import com.ouresports.grimstroke.core.util.CollectionUtil;
import com.ouresports.grimstroke.core.util.WrapperUtil;
import org.apache.ibatis.javassist.NotFoundException;
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
    public InfoCollectionDto getInfoCollectionDto(long id) throws NotFoundException {
        QueryWrapper<InfoCollectionDto> wrapper = generateInfoCollectionDtoWrapper()
                .eq("`cols`.`id`", id)
                .last("LIMIT 1");
        return CollectionUtil.getFirstElement(getInfoCollectionDtos(wrapper));
    }

    @Override
    public InfoCollectionDto getInfoCollectionDto(InfoCollection infoCollection) throws NotFoundException {
        QueryWrapper<InfoCollectionDto> wrapper = generateInfoCollectionDtoWrapper().last("LIMIT 1");
        WrapperUtil.appendEqualQuery(wrapper, infoCollection, "cols");
        return CollectionUtil.getFirstElement(getInfoCollectionDtos(wrapper));
    }

    @Override
    public List<InfoCollectionDto> getInfoCollectionDtos(Wrapper<InfoCollectionDto> wrapper) {
        return baseMapper.selectInfoCollectionDtos(null, wrapper);
    }

    @Override
    public IPage<InfoCollectionDto> getInfoCollectionDtos(IPage<InfoCollectionDto> page, InfoCollection infoCollection) {
        QueryWrapper<InfoCollectionDto> wrapper = generateInfoCollectionDtoWrapper()
                .orderByDesc("`cols`.`sticky`")
                .orderByDesc("`cols`.`created_at`");
        WrapperUtil.appendEqualQuery(wrapper, infoCollection, "cols");
        page.setRecords(baseMapper.selectInfoCollectionDtos(page, wrapper));
        return page;
    }

    @Override
    public IPage<InformationDto> getInformationDtoOfCol(IPage<InformationDto> page, long id) {
        QueryWrapper<InformationDto> wrapper = new QueryWrapper<InformationDto>()
                .eq("`enabled`", true)
                .orderByDesc("`sticky`")
                .orderByDesc("`created_at`");
        page.setRecords(baseMapper.selectInformationDtosOfCol(page, id, wrapper));
        return page;
    }

    private QueryWrapper<InfoCollectionDto> generateInfoCollectionDtoWrapper() {
        return new QueryWrapper<InfoCollectionDto>().groupBy("`cols`.`id`");
    }
}
