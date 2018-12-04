package com.ouresports.grimstroke.core.service;

import com.ouresports.grimstroke.core.GrimstrokeCoreApplicationTest;
import com.ouresports.grimstroke.core.entity.News;
import com.ouresports.grimstroke.core.entity.User;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by will on 2018/11/28.
 */
public class UsersInformationServiceTest extends GrimstrokeCoreApplicationTest {
    @Resource
    UsersInformationService usersInformationService;
    @Resource
    private UserService userService;
    @Resource
    private NewsService newsService;

    @Test
    public void testFindOrCreateBy() {
        User user = userService.findBy(null);
        News news = newsService.findBy(null);
        if (user == null || news == null) {
            return;
        }
        usersInformationService.addUserBrowsable(user, news);
    }
}
