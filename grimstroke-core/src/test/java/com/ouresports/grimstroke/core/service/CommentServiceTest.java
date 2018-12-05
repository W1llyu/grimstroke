package com.ouresports.grimstroke.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.core.GrimstrokeCoreApplicationTest;
import com.ouresports.grimstroke.core.dto.CommentDto;
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

    @Test
    public void testGetCommentDtoPage() throws NotFoundException {
        User user = userService.find(1);
        News news = newsService.find(1);
        Page<CommentDto> page = new Page<>(1, 10);
        IPage<CommentDto> commentDtoIPage = commentService.getCommentDtoPage(page, news, user);
    }
}
