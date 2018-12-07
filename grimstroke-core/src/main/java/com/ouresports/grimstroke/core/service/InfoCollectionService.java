package com.ouresports.grimstroke.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.service.Service;
import com.ouresports.grimstroke.core.dto.InformationDto;
import com.ouresports.grimstroke.core.entity.InfoCollection;

/**
 *
 * @author will
 * @date 2018/11/29
 */
public interface InfoCollectionService extends Service<InfoCollection> {
    /**
     * 获取专栏下所有的资讯
     * @param page
     * @param id
     * @return
     */
    IPage<InformationDto> getInformationDtoOfCol(IPage<InformationDto> page, long id);
}
