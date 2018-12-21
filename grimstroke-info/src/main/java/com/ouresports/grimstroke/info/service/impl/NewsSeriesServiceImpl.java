package com.ouresports.grimstroke.info.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ouresports.grimstroke.base.exception.ServiceException;
import com.ouresports.grimstroke.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.base.util.WrapperUtil;
import com.ouresports.grimstroke.info.entity.News;
import com.ouresports.grimstroke.info.entity.association.NewsSeries;
import com.ouresports.grimstroke.info.mapper.NewsSeriesMapper;
import com.ouresports.grimstroke.info.service.NewsSeriesService;
import org.springframework.stereotype.Service;

import static com.ouresports.grimstroke.info.enums.InfoServiceError.SERIES_ALREADY_IN_NEWS;
import static com.ouresports.grimstroke.info.enums.InfoServiceError.SERIES_NOT_IN_NEWS;

/**
 *
 * @author will
 * @date 2018/12/17
 */
@Service
public class NewsSeriesServiceImpl extends BaseServiceImpl<NewsSeriesMapper, NewsSeries> implements NewsSeriesService{
    @Override
    public void addNewsSeries(News news, long seriesId) throws ServiceException {
        NewsSeries newsSeries = new NewsSeries().setNewsId(news.getId()).setSeriesId(seriesId);
        if (count(newsSeries) > 0) {
            throw new ServiceException(SERIES_ALREADY_IN_NEWS);
        }
        save(newsSeries);
    }

    @Override
    public void removeNewsSeries(News news, long seriesId) throws ServiceException {
        NewsSeries newsSeries = new NewsSeries().setNewsId(news.getId()).setSeriesId(seriesId);
        QueryWrapper<NewsSeries> wrapper = new QueryWrapper<>(newsSeries);
        WrapperUtil.appendEqualQuery(wrapper, newsSeries);
        if (count(newsSeries) == 0) {
            throw new ServiceException(SERIES_NOT_IN_NEWS);
        }
        remove(wrapper);
    }
}
