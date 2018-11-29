package com.ouresports.grimstroke.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.core.GrimstrokeCoreApplicationTest;
import com.ouresports.grimstroke.core.dto.InformationDto;
import com.ouresports.grimstroke.core.entity.Comment;
import com.ouresports.grimstroke.core.entity.News;
import com.ouresports.grimstroke.core.entity.User;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by will on 2018/11/23.
 */
public class NewsServiceTest extends GrimstrokeCoreApplicationTest {
    @Resource
    private CommentService commentService;
    @Resource
    private UserService userService;
    @Resource
    private NewsService newsService;

    @Test
    public void testGetAllInformation() {
        QueryWrapper<InformationDto> wrapper = new QueryWrapper<InformationDto>().eq("game_id", 3).eq("enabled", true);
        IPage<InformationDto> informationDtos = newsService.getAllInformationDtos(new Page<>(1, 2), wrapper);
    }

    public void testAddComment() {
        User user = userService.findBy(null);
        News news = newsService.findBy(null);
        if (user == null || news == null) {
            return;
        }
        Comment comment = commentService.addComment(user, news, "测试评论");
        Assert.assertEquals(comment.getRootType(), news.getCommentableType());
        Assert.assertEquals(comment.getRootId(), news.getId());
        Assert.assertEquals(comment.getTargetType(), news.getCommentableType());
        Assert.assertEquals(comment.getTargetId(), news.getId());
    }
}
