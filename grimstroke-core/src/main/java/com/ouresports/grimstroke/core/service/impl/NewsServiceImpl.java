package com.ouresports.grimstroke.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ouresports.grimstroke.core.base.service.BaseServiceImpl;
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
    public NewsDto getNewsDto(long id) {
        QueryWrapper<NewsDto> wrapper = new QueryWrapper<NewsDto>()
                .eq("`news`.`enabled`", true)
                .eq("`news`.`id`", id)
                .groupBy("`news`.`id`");
        List<NewsDto> list = baseMapper.selectNewsDtos(wrapper);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<NewsDto> getNewsDtos(Wrapper<NewsDto> wrapper) {
        return baseMapper.selectNewsDtos(wrapper);
    }
}
