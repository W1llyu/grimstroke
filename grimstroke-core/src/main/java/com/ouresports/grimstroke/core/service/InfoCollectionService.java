package com.ouresports.grimstroke.core.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.service.Service;
import com.ouresports.grimstroke.core.dto.InformationDto;
import com.ouresports.grimstroke.core.entity.InfoCollection;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/29
 */
public interface InfoCollectionService extends Service<InfoCollection> {
    List<InformationDto> getInformationDtos(Wrapper<InformationDto> wrapper);

    /**
     * 获取专栏下所有的信息流
     * @param page
     * @param infoCollectionId
     * @param wrapper
     * @return
     */
    IPage<InformationDto> getInformationDtos(IPage<InformationDto> page,
                                           long infoCollectionId,
                                           Wrapper<InformationDto> wrapper);
}
