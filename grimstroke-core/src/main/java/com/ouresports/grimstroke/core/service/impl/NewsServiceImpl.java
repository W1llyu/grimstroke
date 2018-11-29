package com.ouresports.grimstroke.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.core.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.core.dto.InformationDto;
import com.ouresports.grimstroke.core.dto.NewsDto;
import com.ouresports.grimstroke.core.entity.News;
import com.ouresports.grimstroke.core.mapper.NewsMapper;
import com.ouresports.grimstroke.core.service.NewsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/22
 */
@Service
public class NewsServiceImpl extends BaseServiceImpl<NewsMapper, News> implements NewsService {
    @Override
    public IPage<NewsDto> getNewsDto(IPage<NewsDto> page, Wrapper<News> wrapper) {
        List<NewsDto> newsDtos = baseMapper.selectNewsDto(page, wrapper == null ? new QueryWrapper<>() : wrapper);
        page.setRecords(newsDtos);
        return page;
    }

    @Override
    public IPage<InformationDto> getAllInformationDto(IPage<InformationDto> page) {
        List<InformationDto> informationDtos = baseMapper.selectAllInformationDto(page);
        page.setRecords(informationDtos);
        return page;
    }
}
