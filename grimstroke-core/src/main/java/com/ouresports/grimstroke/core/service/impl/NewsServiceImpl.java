package com.ouresports.grimstroke.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.core.dto.InformationDto;
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
    public List<InformationDto> getInformationDtos(Wrapper<InformationDto> wrapper) {
        return baseMapper.selectInformationDtos(wrapper);
    }

    @Override
    public IPage<InformationDto> getAllInformationDtos(IPage<InformationDto> page, Wrapper<InformationDto> wrapper) {
        List<InformationDto> informationDtos = baseMapper.selectAllInformationDto(page, wrapper);
        page.setRecords(informationDtos);
        return page;
    }
}
