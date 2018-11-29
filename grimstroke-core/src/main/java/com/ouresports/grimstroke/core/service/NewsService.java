package com.ouresports.grimstroke.core.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.service.Service;
import com.ouresports.grimstroke.core.dto.InformationDto;
import com.ouresports.grimstroke.core.dto.NewsDto;
import com.ouresports.grimstroke.core.entity.News;

/**
 *
 * @author will
 * @date 2018/11/22
 */
public interface NewsService extends Service<News> {
    IPage<NewsDto> getNewsDto(IPage<NewsDto> page, Wrapper<News> wrapper);

    IPage<InformationDto> getAllInformationDto(IPage<InformationDto> page);
}
