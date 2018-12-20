package com.ouresports.grimstroke.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.ouresports.grimstroke.base.concern.Userable;
import com.ouresports.grimstroke.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.base.entity.User;
import com.ouresports.grimstroke.base.mapper.UserMapper;
import com.ouresports.grimstroke.base.service.UserService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author will
 * @date 2018/11/22
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
    @Override
    public <T extends Userable>void includeUsers(Collection<T> cols) {
        Set<Long> userIds = Sets.newHashSet();
        for (Userable userable: cols) {
            if (userable.getUserId() != null) {
                userIds.add(userable.getUserId());
            }
        }
        Collection<User> users = listByIds(userIds);
        Map<Long, User> userMap = Maps.newHashMap();
        for (User user: users) {
            userMap.put(user.getId(), user);
        }
        for (Userable userable: cols) {
            userable.setUser(userMap.get(userable.getUserId()));
        }
    }

    @Override
    public void includeUser(Userable userable) {
        if (userable.getUserId() != null) {
            try {
                userable.setUser(find(userable.getUserId()));
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
