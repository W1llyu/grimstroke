package com.ouresports.grimstroke.info.service;

import com.ouresports.grimstroke.base.exception.ServiceException;
import com.ouresports.grimstroke.base.service.Service;
import com.ouresports.grimstroke.info.entity.News;
import com.ouresports.grimstroke.info.entity.association.NewsTeam;

/**
 *
 * @author will
 * @date 2018/12/17
 */
public interface NewsTeamService extends Service<NewsTeam> {
    void addNewsTeam(News news, long teamId) throws ServiceException;

    void removeNewsTeam(News news, long teamId) throws ServiceException;
}
