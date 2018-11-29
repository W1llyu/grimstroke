package com.ouresports.grimstroke.core.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.service.Service;
import com.ouresports.grimstroke.core.dto.InfoCollectionDto;
import com.ouresports.grimstroke.core.entity.InfoCollection;

/**
 *
 * @author will
 * @date 2018/11/29
 */
public interface InfoCollectionService extends Service<InfoCollection> {
    IPage<InfoCollectionDto> getNewsDto(IPage<InfoCollectionDto> page, Wrapper<InfoCollection> wrapper);
}
