package com.ouresports.grimstroke.core.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.ouresports.grimstroke.core.base.service.Service;
import com.ouresports.grimstroke.core.dto.NewsDto;
import com.ouresports.grimstroke.core.entity.News;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/22
 */
public interface NewsService extends Service<News> {
    /**
     * 获得一条资讯详情
     * @param id
     * @return
     */
    NewsDto getNewsDto(long id);

    /**
     * 获得资讯DTO列表
     * @param wrapper
     * @return
     */
    List<NewsDto> getNewsDtos(Wrapper<NewsDto> wrapper);
}
