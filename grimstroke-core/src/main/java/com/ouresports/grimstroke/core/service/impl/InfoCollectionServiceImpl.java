package com.ouresports.grimstroke.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.core.dto.InfoCollectionDto;
import com.ouresports.grimstroke.core.entity.InfoCollection;
import com.ouresports.grimstroke.core.mapper.InfoCollectionMapper;
import com.ouresports.grimstroke.core.service.InfoCollectionService;
import org.springframework.stereotype.Service;

/**
 *
 * @author will
 * @date 2018/11/29
 */
@Service
public class InfoCollectionServiceImpl extends BaseServiceImpl<InfoCollectionMapper, InfoCollection> implements InfoCollectionService {
    @Override
    public IPage<InfoCollectionDto> getNewsDto(IPage<InfoCollectionDto> page, Wrapper<InfoCollection> wrapper) {
        return null;
    }
}
