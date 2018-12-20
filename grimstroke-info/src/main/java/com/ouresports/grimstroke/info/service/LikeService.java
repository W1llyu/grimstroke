package com.ouresports.grimstroke.info.service;

import com.ouresports.grimstroke.base.service.Service;
import com.ouresports.grimstroke.info.concern.Likable;
import com.ouresports.grimstroke.info.entity.Like;
import com.ouresports.grimstroke.base.entity.User;
import com.ouresports.grimstroke.base.exception.ServiceException;

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
    void addLike(User user, Likable likable) throws ServiceException;

    /**
     * 取消点赞
     * @param user
     * @param likable
     */
    void removeLike(User user, Likable likable) throws ServiceException;
}
