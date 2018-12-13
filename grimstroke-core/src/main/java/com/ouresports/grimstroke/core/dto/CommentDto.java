package com.ouresports.grimstroke.core.dto;

import com.ouresports.grimstroke.core.base.entity.BaseTo;
import com.ouresports.grimstroke.core.concern.MessageTriggerable;
import com.ouresports.grimstroke.core.concern.Targetable;
import com.ouresports.grimstroke.core.entity.Comment;
import com.ouresports.grimstroke.core.entity.News;
import com.ouresports.grimstroke.core.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

import static com.ouresports.grimstroke.core.enums.InformationSubType.Analysis;

/**
 *
 * @author will
 * @date 2018/11/29
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentDto extends BaseTo<Comment> implements Targetable, MessageTriggerable {
    private Long id;
    private Long userId;
    private String rootType;
    private Long rootId;
    private Long rootCommentId;
    private Long parentCommentId;
    private String content;
    private Boolean enabled;
    private Long subCommentCount;
    private List<SubCommentDto> subComments;
    private Boolean liked;
    private Long likeCount;
    private Date createdAt;
    private NormalUserDto user;
    private Object target;

    @Override
    public String getTargetType() {
        return rootCommentId == null ? rootType : "Comment";
    }

    @Override
    public Long getTargetId() {
        return rootCommentId == null ? rootId : parentCommentId;
    }

    @Override
    public String getTriggerableType() {
        return "Comment";
    }

    @Override
    public User getNotifyUser() {
        if (getTarget() == null) {
            return null;
        }
        User user = new User();
        if (getTarget().getClass() == News.class) {
            News news = (News) getTarget();
            if (news.getType() == Analysis) {
                user.setId(news.getAssociateId());
            } else {
                return null;
            }
            user.setId(news.getAssociateId());
        } else if (getTarget().getClass() == Comment.class) {
            Comment comment = (Comment) getTarget();
            user.setId(comment.getUserId());
        } else {
            return null;
        }
        return user;
    }
}
