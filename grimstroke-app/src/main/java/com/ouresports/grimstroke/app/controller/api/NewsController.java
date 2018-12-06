package com.ouresports.grimstroke.app.controller.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.app.base.template.PaginationTemplate;
import com.ouresports.grimstroke.app.base.template.ResultTemplate;
import com.ouresports.grimstroke.app.base.template.SingleTemplate;
import com.ouresports.grimstroke.app.rbo.api.CommentRbo;
import com.ouresports.grimstroke.app.vo.api.CommentVo;
import com.ouresports.grimstroke.app.vo.api.NewsVo;
import com.ouresports.grimstroke.core.dto.CommentDto;
import com.ouresports.grimstroke.core.dto.NewsDto;
import com.ouresports.grimstroke.core.service.CommentService;
import com.ouresports.grimstroke.core.service.NewsService;
import com.ouresports.grimstroke.core.service.UsersInformationService;
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
        authenticateUser();
        NewsDto dto = newsService.getNewsDto(id);
        if (currentUser != null) {
            usersNewsService.addUserBrowsable(currentUser, newsService.find(id));
        }
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
        Page<CommentDto> page = new Page<>(currentPage, per);
        IPage<CommentDto> commentDtoIPage = commentService.getCommentDtoPage(page, newsService.find(id), currentUser);
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
        commentService.addComment(currentUser, newsService.find(id), comment.getContent());
        return render(ResultTemplate.createOk());
    }

    /**
     * 增加用户浏览记录
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping(value="/{id}/browse")
    public ResponseEntity browse(@PathVariable long id) throws Exception {
        authenticateUserForce();
        usersNewsService.addUserBrowsable(currentUser, newsService.find(id));
        return render(ResultTemplate.createOk());
    }
}
