package com.ouresports.grimstroke.core.service;

import com.ouresports.grimstroke.core.base.service.Service;
import com.ouresports.grimstroke.core.concern.Commentable;
import com.ouresports.grimstroke.core.entity.Comment;
import com.ouresports.grimstroke.core.entity.User;

/**
 *
 * @author will
 * @date 2018/11/27
 */
public interface CommentService extends Service<Comment> {
    /**
     * 添加一条评论
     * @param user
     * @param commentable
     * @param content
     */
    Comment addComment(User user, Commentable commentable, String content);
}
