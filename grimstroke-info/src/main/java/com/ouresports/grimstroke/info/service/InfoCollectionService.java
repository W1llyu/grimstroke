package com.ouresports.grimstroke.info.service;

import com.ouresports.grimstroke.base.service.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.info.dto.InformationDto;
import com.ouresports.grimstroke.info.entity.InfoCollection;

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
