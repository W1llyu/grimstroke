package com.ouresports.grimstroke.info.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ouresports.grimstroke.base.exception.ServiceException;
import com.ouresports.grimstroke.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.base.util.WrapperUtil;
import com.ouresports.grimstroke.info.entity.News;
import com.ouresports.grimstroke.info.entity.association.NewsTeam;
import com.ouresports.grimstroke.info.mapper.NewsTeamMapper;
import com.ouresports.grimstroke.info.service.NewsTeamService;
import org.springframework.stereotype.Service;

import static com.ouresports.grimstroke.base.enums.ServiceError.TEAM_ALREADY_IN_NEWS;
import static com.ouresports.grimstroke.base.enums.ServiceError.TEAM_NOT_IN_NEWS;

/**
 *
 * @author will
 * @date 2018/12/17
 */
@Service
public class NewsTeamServiceImpl extends BaseServiceImpl<NewsTeamMapper, NewsTeam> implements NewsTeamService {
    @Override
    public void addNewsTeam(News news, long teamId) throws ServiceException {
        NewsTeam newsTeam = new NewsTeam().setNewsId(news.getId()).setTeamId(teamId);
        if (count(newsTeam) > 0) {
            throw new ServiceException(TEAM_ALREADY_IN_NEWS);
        }
        save(newsTeam);
    }

    @Override
    public void removeNewsTeam(News news, long teamId) throws ServiceException {
        NewsTeam newsTeam = new NewsTeam().setNewsId(news.getId()).setTeamId(teamId);
        QueryWrapper<NewsTeam> wrapper = new QueryWrapper<>(newsTeam);
        WrapperUtil.appendEqualQuery(wrapper, newsTeam);
        if (count(newsTeam) == 0) {
            throw new ServiceException(TEAM_NOT_IN_NEWS);
        }
        remove(wrapper);
    }
}
