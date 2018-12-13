package com.ouresports.grimstroke.core.dto;

import com.ouresports.grimstroke.core.base.entity.BaseTo;
import com.ouresports.grimstroke.core.concern.MessageTriggerable;
import com.ouresports.grimstroke.core.concern.Targetable;
import com.ouresports.grimstroke.core.entity.Comment;
import com.ouresports.grimstroke.core.entity.Like;
import com.ouresports.grimstroke.core.entity.User;
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
