package com.ouresports.grimstroke.core.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.service.Service;
import com.ouresports.grimstroke.core.dto.InformationDto;
import com.ouresports.grimstroke.core.entity.News;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/22
 */
public interface NewsService extends Service<News> {
    List<InformationDto> getInformationDtos(Wrapper<InformationDto> wrapper);

    /**
     * 获取所有的信息流
     * @param page
     * @param wrapper
     * @return
     */
    IPage<InformationDto> getAllInformationDtos(IPage<InformationDto> page, Wrapper<InformationDto> wrapper);
}
