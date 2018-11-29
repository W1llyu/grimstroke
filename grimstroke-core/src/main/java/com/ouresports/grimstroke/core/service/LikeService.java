package com.ouresports.grimstroke.core.service;

import com.ouresports.grimstroke.core.base.service.Service;
import com.ouresports.grimstroke.core.concern.Likable;
import com.ouresports.grimstroke.core.entity.Like;
import com.ouresports.grimstroke.core.entity.User;

/**
 *
 * @author will
 * @date 2018/11/29
 */
public interface LikeService extends Service<Like> {
    /**
     * 点赞
     * @param user
     * @param likable
     */
    void addLike(User user, Likable likable);
}