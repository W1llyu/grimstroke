package com.ouresports.grimstroke.core.service.impl;

import com.ouresports.grimstroke.core.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.core.concern.Likable;
import com.ouresports.grimstroke.core.entity.Like;
import com.ouresports.grimstroke.core.entity.User;
import com.ouresports.grimstroke.core.mapper.LikeMapper;
import com.ouresports.grimstroke.core.service.LikeService;
import org.springframework.stereotype.Service;

/**
 *
 * @author will
 * @date 2018/11/29
 */
@Service
public class LikeServiceImpl extends BaseServiceImpl<LikeMapper, Like> implements LikeService {
    @Override
    public void addLike(User user, Likable likable) {
        Like like = new Like()
                .setUserId(user.getId())
                .setTargetType(likable.getLikableType())
                .setTargetId(likable.getId());
        findOrCreateBy(like);
    }
}
