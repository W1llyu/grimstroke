package com.ouresports.grimstroke.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.service.Service;
import com.ouresports.grimstroke.core.dto.NewsDto;
import com.ouresports.grimstroke.core.entity.News;
import org.apache.ibatis.javassist.NotFoundException;

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
     * @throws NotFoundException
     */
    NewsDto getNewsDto(long id) throws NotFoundException;

    /**
     * 获得一条资讯详情
     * @param news
     * @return
     * @throws NotFoundException
     */
    NewsDto getNewsDto(News news) throws NotFoundException;

    /**
     * 获得资讯DTO列表
     * @param wrapper
     * @return
     */
    List<NewsDto> getNewsDtos(QueryWrapper<NewsDto> wrapper);

    /**
     * 获得资讯DTO分页
     * @param page
     * @param news
     * @return
     */
    IPage<NewsDto> getNewsDtos(IPage<NewsDto> page, News news);
}
