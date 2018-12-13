package com.ouresports.grimstroke.core.service;

import com.ouresports.grimstroke.core.GrimstrokeCoreApplicationTest;
import com.ouresports.grimstroke.core.entity.Comment;
import com.ouresports.grimstroke.core.entity.User;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by will on 2018/12/14.
 */
public class LikeServiceTest extends GrimstrokeCoreApplicationTest {
    @Resource
    private LikeService likeService;
    @Resource
    private CommentService commentService;
    @Resource
    private UserService userService;

    @Test
    public void testAddLike() throws Exception {
        User user = userService.find(1);
        Comment comment = commentService.find(1);
        likeService.addLike(user, comment);
    }
}
