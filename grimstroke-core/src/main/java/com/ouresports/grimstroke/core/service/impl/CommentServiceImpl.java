package com.ouresports.grimstroke.core.service.impl;

import com.ouresports.grimstroke.core.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.core.concern.Commentable;
import com.ouresports.grimstroke.core.entity.Comment;
import com.ouresports.grimstroke.core.entity.User;
import com.ouresports.grimstroke.core.mapper.CommentMapper;
import com.ouresports.grimstroke.core.service.CommentService;
import org.springframework.stereotype.Service;

/**
 *
 * @author will
 * @date 2018/11/27
 */
@Service
public class CommentServiceImpl extends BaseServiceImpl<CommentMapper, Comment> implements CommentService {
    @Override
    public Comment addComment(User user, Commentable commentable, String content) {
        String rootType;
        long rootId;
        // 回复评论并且该父评论为也是对评论的回复时
        if (commentable.getClass() == Comment.class && ((Comment) commentable).getRootType().equals(Comment.class.getSimpleName())) {
            Comment parentComment = (Comment) commentable;
            rootType = parentComment.getRootType();
            rootId = parentComment.getRootId();
        } else {
            rootType = commentable.getCommentableType();
            rootId = commentable.getId();
        }
        Comment comment = new Comment()
                .setUserId(user.getId())
                .setRootType(rootType)
                .setRootId(rootId)
                .setTargetType(commentable.getCommentableType())
                .setTargetId(commentable.getId())
                .setContent(content);
        save(comment);
        return comment;
    }
}
