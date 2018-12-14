package com.ouresports.grimstroke.core.service;

import com.ouresports.grimstroke.core.GrimstrokeCoreApplicationTest;
import com.ouresports.grimstroke.core.entity.InfoCollection;
import com.ouresports.grimstroke.core.entity.News;
import com.ouresports.grimstroke.core.exception.ServiceException;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by will on 2018/11/29.
 */
public class InfoCollectionsInformationServiceTest extends GrimstrokeCoreApplicationTest {
    @Resource
    private InfoCollectionsInformationService infoCollectionsInformationService;
    @Resource
    private InfoCollectionService infoCollectionService;
    @Resource
    private NewsService newsService;

    @Test
    public void testAddInformation() throws ServiceException {
        News news = newsService.findBy(null);
        InfoCollection infoCollection = infoCollectionService.findBy(null);
        if (news == null || infoCollection == null) {
            return;
        }
        infoCollectionsInformationService.addInformation(infoCollection, news);
    }
}
