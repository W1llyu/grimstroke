package com.ouresports.grimstroke.core.service;

import com.ouresports.grimstroke.core.GrimstrokeCoreApplicationTest;
import com.ouresports.grimstroke.core.entity.User;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by will on 2018/11/22.
 */
public class UserServiceTest extends GrimstrokeCoreApplicationTest {
    @Resource
    private UserService userService;

    @Test
    public void testSelect() {
        User user = userService.find(7972);
    }
}
