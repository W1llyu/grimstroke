package com.ouresports.grimstroke.core.mapper;

import com.ouresports.grimstroke.core.GrimstrokeCoreApplicationTest;
import com.ouresports.grimstroke.core.entity.News;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by will on 2018/11/22.
 */
public class NewsMapperTest extends GrimstrokeCoreApplicationTest {
    @Resource
    private NewsMapper newsMapper;

    @Test
    public void testSelect() {
        List<News> news = newsMapper.selectList(null);
        Assert.assertNotNull(news);
    }
}
