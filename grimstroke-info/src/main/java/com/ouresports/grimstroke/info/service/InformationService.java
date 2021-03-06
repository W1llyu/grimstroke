package com.ouresports.grimstroke.info.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.info.dto.InformationDto;

/**
 *
 * @author will
 * @date 2018/12/3
 */
public interface InformationService {
    /**
     * 信息流列表分页
     * @param page
     * @param gameId
     * @return
     */
    IPage<InformationDto> getOrderedInformation(int page, Integer gameId);
}
