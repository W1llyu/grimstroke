package com.ouresports.grimstroke.info.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ouresports.grimstroke.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.info.concern.Likable;
import com.ouresports.grimstroke.info.dto.LikeDto;
import com.ouresports.grimstroke.info.entity.Like;
import com.ouresports.grimstroke.base.entity.User;
import com.ouresports.grimstroke.base.exception.ServiceException;
import com.ouresports.grimstroke.info.mapper.LikeMapper;
import com.ouresports.grimstroke.info.service.LikeService;
import com.ouresports.grimstroke.info.service.UserMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ouresports.grimstroke.base.enums.ServiceError.ALREADY_LIKED;
import static com.ouresports.grimstroke.base.enums.ServiceError.NOT_LIKED_YET;

/**
 *
 * @author will
 * @date 2018/11/29
 */
@Service
public class LikeServiceImpl extends BaseServiceImpl<LikeMapper, Like> implements LikeService {
    @Resource
    private UserMessageService userMessageService;

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
        userNotify(likable, like);
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

    private void userNotify(Likable likable, Like like) throws ServiceException {
        LikeDto dto = (LikeDto) new LikeDto().convertFor(like);
        dto.setTarget(likable);
        userMessageService.addUserMessage(dto);
    }
}
