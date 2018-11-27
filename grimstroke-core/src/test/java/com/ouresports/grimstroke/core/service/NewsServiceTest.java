package com.ouresports.grimstroke.core.service;

import com.ouresports.grimstroke.core.BaseTest;
import com.ouresports.grimstroke.core.entity.Comment;
import com.ouresports.grimstroke.core.entity.News;
import com.ouresports.grimstroke.core.entity.User;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by will on 2018/11/23.
 */
public class NewsServiceTest extends BaseTest {
    @Resource
    private CommentService commentService;
    @Resource
    private UserService userService;
    @Resource
    private NewsService newsService;

    @Test
    public void testAddComment() {
        User user = userService.findBy(null);
        News news = newsService.findBy(null);
        Comment comment = commentService.addComment(user, news, "测试评论");
        Assert.assertEquals(comment.getRootType(), news.getCommentableType());
        Assert.assertEquals(comment.getRootId(), news.getId());
        Assert.assertEquals(comment.getTargetType(), news.getCommentableType());
        Assert.assertEquals(comment.getTargetId(), news.getId());
    }
}
