package com.ouresports.grimstroke.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.core.dto.NewsDto;
import com.ouresports.grimstroke.core.entity.News;
import com.ouresports.grimstroke.core.mapper.NewsMapper;
import com.ouresports.grimstroke.core.service.NewsService;
import com.ouresports.grimstroke.core.util.CollectionUtil;
import com.ouresports.grimstroke.core.util.WrapperUtil;
import org.apache.ibatis.javassist.NotFoundException;
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
    public NewsDto getNewsDto(long id) throws NotFoundException  {
        QueryWrapper<NewsDto> wrapper = generateWrapper()
                .eq("`news`.`id`", id)
                .last("LIMIT 1");
        return CollectionUtil.getFirstElement(baseMapper.selectNewsDtos(null, wrapper));
    }

    @Override
    public NewsDto getNewsDto(News news) throws NotFoundException {
        QueryWrapper<NewsDto> wrapper = generateWrapper().last("LIMIT 1");
        WrapperUtil.appendEqualQuery(wrapper, news, "news");
        return CollectionUtil.getFirstElement(baseMapper.selectNewsDtos(null, wrapper));
    }

    @Override
    public List<NewsDto> getNewsDtos(QueryWrapper<NewsDto> wrapper) {
        wrapper.groupBy("`news`.`id`");
        return baseMapper.selectNewsDtos(null, wrapper);
    }

    @Override
    public IPage<NewsDto> getNewsDtos(IPage<NewsDto> page, News news) {
        QueryWrapper<NewsDto> wrapper = generateWrapper()
                .orderByDesc("`news`.`sticky`")
                .orderByDesc("`news`.`created_at`");
        WrapperUtil.appendEqualQuery(wrapper, news, "news");
        page.setRecords(baseMapper.selectNewsDtos(page, wrapper));
        return page;
    }

    private QueryWrapper<NewsDto> generateWrapper() {
        return new QueryWrapper<NewsDto>()
                .groupBy("`news`.`id`");
    }
}
