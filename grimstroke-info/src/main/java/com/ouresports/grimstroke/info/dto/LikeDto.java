package com.ouresports.grimstroke.info.dto;

import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.info.concern.MessageTriggerable;
import com.ouresports.grimstroke.info.concern.Targetable;
import com.ouresports.grimstroke.info.entity.Comment;
import com.ouresports.grimstroke.info.entity.Like;
import com.ouresports.grimstroke.base.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/12/13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LikeDto extends BaseTo<Like> implements Targetable, MessageTriggerable {
    private Long id;
    private Long userId;
    private String targetType;
    private Long targetId;
    private Date createdAt;
    private Object target;
    private NormalUserDto user;

    @Override
    public String getTriggerableType() {
        return "Like";
    }

    @Override
    public User getNotifyUser() {
        if (getTarget() == null) {
            return null;
        }
        User user = new User();
        if (getTarget().getClass() == Comment.class) {
            Comment comment = (Comment) getTarget();
            user.setId(comment.getUserId());
        } else {
            return null;
        }
        return user;
    }
}
