package com.ouresports.grimstroke.info.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.info.dto.InformationDto;
import com.ouresports.grimstroke.info.entity.InfoCollection;
import com.ouresports.grimstroke.info.mapper.InfoCollectionMapper;
import com.ouresports.grimstroke.info.service.InfoCollectionService;
import org.springframework.stereotype.Service;

/**
 *
 * @author will
 * @date 2018/11/29
 */
@Service
public class InfoCollectionServiceImpl extends BaseServiceImpl<InfoCollectionMapper, InfoCollection> implements InfoCollectionService {
    @Override
    public IPage<InformationDto> getInformationDtoOfCol(IPage<InformationDto> page, long id) {
        QueryWrapper<InformationDto> wrapper = new QueryWrapper<InformationDto>()
                .eq("`enabled`", true)
                .orderByDesc("`sticky`")
                .orderByDesc("`created_at`");
        page.setRecords(baseMapper.selectInformationDtosOfCol(page, id, wrapper));
        return page;
    }
}
