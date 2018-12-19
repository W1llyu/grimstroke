package com.ouresports.grimstroke.info.service;

import com.ouresports.grimstroke.base.exception.ServiceException;
import com.ouresports.grimstroke.base.service.Service;
import com.ouresports.grimstroke.info.entity.News;
import com.ouresports.grimstroke.info.entity.association.NewsSeries;

/**
 *
 * @author will
 * @date 2018/12/17
 */
public interface NewsSeriesService extends Service<NewsSeries> {
    void addNewsSeries(News news, long seriesId) throws ServiceException;

    void removeNewsSeries(News news, long seriesId) throws ServiceException;
}
