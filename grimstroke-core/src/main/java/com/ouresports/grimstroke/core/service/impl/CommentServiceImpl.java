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
        String rootType, subRootType = null;
        Long rootId, subRootId = null;
        // 回复评论时
        if (commentable.getClass() == Comment.class) {
            Comment parentComment = (Comment) commentable;
            // 父评论也是对评论的回复
            if (parentComment.getSubRootType() != null) {
                subRootType = parentComment.getSubRootType();
                subRootId = parentComment.getSubRootId();
            } else {
                // 父评论是对信息流的评论
                subRootType = parentComment.getCommentableType();
                subRootId = parentComment.getId();
            }
            rootType = parentComment.getRootType();
            rootId = parentComment.getRootId();
        } else {
            // 直接对信息流评论
            rootType = commentable.getCommentableType();
            rootId = commentable.getId();
        }
        Comment comment = new Comment()
                .setUserId(user.getId())
                .setRootType(rootType)
                .setRootId(rootId)
                .setSubRootType(subRootType)
                .setSubRootId(subRootId)
                .setTargetType(commentable.getCommentableType())
                .setTargetId(commentable.getId())
                .setContent(content);
        save(comment);
        return comment;
    }
}
