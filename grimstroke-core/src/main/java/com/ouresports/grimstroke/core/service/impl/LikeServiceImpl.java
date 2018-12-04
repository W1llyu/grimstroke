package com.ouresports.grimstroke.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ouresports.grimstroke.core.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.core.concern.Likable;
import com.ouresports.grimstroke.core.entity.Like;
import com.ouresports.grimstroke.core.entity.User;
import com.ouresports.grimstroke.core.exception.ServiceException;
import com.ouresports.grimstroke.core.mapper.LikeMapper;
import com.ouresports.grimstroke.core.service.LikeService;
import org.springframework.stereotype.Service;

import static com.ouresports.grimstroke.core.enums.ServiceError.ALREADY_LIKED;
import static com.ouresports.grimstroke.core.enums.ServiceError.NOT_LIKED_YET;

/**
 *
 * @author will
 * @date 2018/11/29
 */
@Service
public class LikeServiceImpl extends BaseServiceImpl<LikeMapper, Like> implements LikeService {
    @Override
    public void addLike(User user, Likable likable) throws ServiceException {
        QueryWrapper<Like> wrapper = new QueryWrapper<Like>()
                .eq("user_id", user.getId())
                .eq("target_type", likable.getLikableType())
                .eq("target_id", likable.getId());
        if (count(wrapper) > 0) {
            throw new ServiceException(ALREADY_LIKED);
        }
        Like like = new Like()
                .setUserId(user.getId())
                .setTargetType(likable.getLikableType())
                .setTargetId(likable.getId());
        findOrCreateBy(like);
    }

    @Override
    public void removeLike(User user, Likable likable) throws ServiceException {
        QueryWrapper<Like> wrapper = new QueryWrapper<Like>()
                .eq("user_id", user.getId())
                .eq("target_type", likable.getLikableType())
                .eq("target_id", likable.getId());
        if (count(wrapper) == 0) {
            throw new ServiceException(NOT_LIKED_YET);
        }
        remove(wrapper);
    }
}
