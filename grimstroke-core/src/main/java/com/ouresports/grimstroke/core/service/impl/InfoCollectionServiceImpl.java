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
    public InfoCollectionDto getInfoCollectionDto(long id) {
        QueryWrapper<InfoCollectionDto> wrapper = new QueryWrapper<InfoCollectionDto>()
                .eq("`cols`.`enabled`", true)
                .eq("`cols`.`id`", id)
                .last("LIMIT 1");
        List<InfoCollectionDto> list = getInfoCollectionDtos(wrapper);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<InfoCollectionDto> getInfoCollectionDtos(Wrapper<InfoCollectionDto> wrapper) {
        return baseMapper.selectInfoCollectionDtos(null, wrapper);
    }

    @Override
    public IPage<InfoCollectionDto> getInfoCollectionDtos(IPage<InfoCollectionDto> page, Integer gameId) {
        QueryWrapper<InfoCollectionDto> wrapper = new QueryWrapper<InfoCollectionDto>()
                .eq("`cols`.`enabled`", true)
                .orderByDesc("`cols`.`sticky`")
                .orderByDesc("`cols`.`created_at`")
                .groupBy("`cols`.`id`");
        if (gameId != null) {
            wrapper.eq("`cols`.`game_id`", gameId);
        }
        List<InfoCollectionDto> list = baseMapper.selectInfoCollectionDtos(page, wrapper);
        page.setRecords(list);
        return page;
    }

    @Override
    public IPage<InformationDto> getInformationDtoOfCol(IPage<InformationDto> page, long id) {
        QueryWrapper<InformationDto> wrapper = new QueryWrapper<InformationDto>()
                .eq("`enabled`", true)
                .orderByDesc("`sticky`")
                .orderByDesc("`created_at`");
        List<InformationDto> list = baseMapper.selectInformationDtosOfCol(page, id, wrapper);
        page.setRecords(list);
        return page;
    }
}
