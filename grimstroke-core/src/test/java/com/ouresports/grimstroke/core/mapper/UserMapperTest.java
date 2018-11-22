package com.ouresports.grimstroke.core.mapper;

import com.ouresports.grimstroke.core.BaseTest;
import com.ouresports.grimstroke.core.entity.User;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by will on 2018/11/22.
 */
public class UserMapperTest extends BaseTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        List<User> users = userMapper.selectList(null);
        Assert.assertNotNull(users);
    }
}
