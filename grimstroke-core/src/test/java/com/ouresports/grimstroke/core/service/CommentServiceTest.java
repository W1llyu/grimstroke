package com.ouresports.grimstroke.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.core.GrimstrokeCoreApplicationTest;
import com.ouresports.grimstroke.core.dto.CommentDto;
import com.ouresports.grimstroke.core.entity.Comment;
import com.ouresports.grimstroke.core.entity.News;
import com.ouresports.grimstroke.core.entity.User;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by will on 2018/11/27.
 */
public class CommentServiceTest extends GrimstrokeCoreApplicationTest {
    @Resource
    private CommentService commentService;
    @Resource
    private NewsService newsService;
    @Resource
    private UserService userService;

    public void testGetCommentDtoPage() throws NotFoundException {
        User user = userService.find(1);
        News news = newsService.find(1);
        Page<CommentDto> page = new Page<>(1, 10);
        IPage<CommentDto> commentDtoIPage = commentService.getCommentDtos(page, news, user);
    }

    public void testCommentNews() throws Exception {
        User user = userService.find(1);
        News news = newsService.find(6);
        commentService.addComment(user, news, "hah");
    }

    @Test
    public void replyComment() throws Exception {
        User user = userService.find(1);
        QueryWrapper<Comment> wrapper = new QueryWrapper<Comment>()
                .ne("`user_id`", 1);
        Comment comment = commentService.findBy(wrapper);
        commentService.addComment(user, comment, "hah");
    }
}
