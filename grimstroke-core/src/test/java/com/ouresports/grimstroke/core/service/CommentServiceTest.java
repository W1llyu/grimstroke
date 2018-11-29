package com.ouresports.grimstroke.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ouresports.grimstroke.core.GrimstrokeCoreApplicationTest;
import com.ouresports.grimstroke.core.entity.Comment;
import com.ouresports.grimstroke.core.entity.User;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by will on 2018/11/27.
 */
public class CommentServiceTest extends GrimstrokeCoreApplicationTest {
    @Resource
    private CommentService commentService;
    @Resource
    private UserService userService;

    @Test
    public void testReplyComment() {
        User user = userService.findBy(null);
        Comment commentable = commentService.findBy(null);
        Comment comment = commentService.addComment(user, commentable, "测试回复评论");
        Assert.assertEquals(comment.getRootType(), commentable.getCommentableType());
        Assert.assertEquals(comment.getRootId(), commentable.getId());
        Assert.assertEquals(comment.getTargetType(), commentable.getCommentableType());
        Assert.assertEquals(comment.getTargetId(), commentable.getId());
    }

    @Test
    public void testReplyReplication() {
        User user = userService.findBy(null);
        Comment commentable = commentService.findBy(new QueryWrapper<Comment>().eq("root_type", Comment.class.getSimpleName()));
        if (commentable == null || user == null) {
            return;
        }
        Comment comment = commentService.addComment(user, commentable, "测试回复回复");
        Assert.assertEquals(comment.getRootType(), commentable.getRootType());
        Assert.assertEquals(comment.getRootId(), commentable.getRootId());
        Assert.assertEquals(comment.getTargetType(), commentable.getCommentableType());
        Assert.assertEquals(comment.getTargetId(), commentable.getId());
    }
}
