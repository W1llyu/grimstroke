package com.ouresports.grimstroke.app.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.app.base.template.PaginationTemplate;
import com.ouresports.grimstroke.app.base.template.ResultTemplate;
import com.ouresports.grimstroke.app.base.template.SingleTemplate;
import com.ouresports.grimstroke.app.rbo.CommentRbo;
import com.ouresports.grimstroke.app.vo.CommentVo;
import com.ouresports.grimstroke.app.vo.NewsVo;
import com.ouresports.grimstroke.core.dto.CommentDto;
import com.ouresports.grimstroke.core.dto.NewsDto;
import com.ouresports.grimstroke.core.entity.News;
import com.ouresports.grimstroke.core.service.CommentService;
import com.ouresports.grimstroke.core.service.NewsService;
import com.ouresports.grimstroke.core.service.UsersInformationService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *
 * @author will
 * @date 2018/11/23
 */
@RestController
@RequestMapping(value="/api/news", produces="application/json;charset=UTF-8")
public class NewsController extends BaseController {
    @Resource
    private NewsService newsService;
    @Resource
    private UsersInformationService usersNewsService;
    @Resource
    private CommentService commentService;

    /**
     * 资讯详情
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{id}")
    public ResponseEntity show(@PathVariable long id) throws Exception {
        News news = loadNewsById(id);
        NewsDto dto = newsService.getNewsDto(news.getId());
        return render(new SingleTemplate<>(dto, NewsVo.class));
    }

    /**
     * 获得资讯的评论
     * @param id
     * @param currentPage
     * @param per
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{id}/comments")
    public ResponseEntity comments(@PathVariable long id,
                                   @RequestParam(value="page", defaultValue="1") int currentPage,
                                   @RequestParam(defaultValue="10") int per) throws Exception {
        authenticateUser();
        News news = loadNewsById(id);
        Page<CommentDto> page = new Page<>(currentPage, per);
        IPage<CommentDto> commentDtoIPage = commentService.getCommentDtoPage(page, news, currentUser);
        return render(new PaginationTemplate<>(commentDtoIPage, CommentVo.class));
    }

    /**
     * 评论资讯
     * @param id
     * @param comment
     * @return
     * @throws Exception
     */
    @PostMapping(value="/{id}/comments")
    public ResponseEntity addComment(@PathVariable long id,
                                     @Valid @RequestBody CommentRbo comment) throws Exception {
        authenticateUserForce();
        commentService.addComment(currentUser, loadNewsById(id), comment.getContent());
        return render(ResultTemplate.createOk());
    }

    /**
     * 增加用户浏览记录
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping(value="/{id}/read")
    public ResponseEntity read(@PathVariable long id) throws Exception {
        authenticateUserForce();
        News news = loadNewsById(id);
        usersNewsService.addUserBrowsable(currentUser, news);
        return render(ResultTemplate.createOk());
    }

    private News loadNewsById(long id) throws Exception {
        News news = newsService.find(id);
        if (news == null) {
            throw new NotFoundException("News");
        }
        return news;
    }
}
