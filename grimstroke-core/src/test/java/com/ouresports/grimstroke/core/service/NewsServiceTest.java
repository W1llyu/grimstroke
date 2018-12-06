package com.ouresports.grimstroke.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.core.GrimstrokeCoreApplicationTest;
import com.ouresports.grimstroke.core.dto.NewsDto;
import com.ouresports.grimstroke.core.entity.News;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by will on 2018/11/23.
 */
public class NewsServiceTest extends GrimstrokeCoreApplicationTest {
    @Resource
    private NewsService newsService;

    @Test
    public void testGetNewsDtos() {
        News news = new News().setGameId(1);
        IPage<NewsDto> newsDtos = newsService.getNewsDtos(new Page<>(1, 10), news);
    }
}
