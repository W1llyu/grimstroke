package com.ouresports.grimstroke.info.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.base.annotation.AuthToken;
import com.ouresports.grimstroke.base.template.PaginationTemplate;
import com.ouresports.grimstroke.base.template.ResultTemplate;
import com.ouresports.grimstroke.base.template.SingleTemplate;
import com.ouresports.grimstroke.info.rbo.api.CommentRbo;
import com.ouresports.grimstroke.info.vo.api.CommentVo;
import com.ouresports.grimstroke.info.vo.api.NewsVo;
import com.ouresports.grimstroke.info.dto.CommentDto;
import com.ouresports.grimstroke.info.dto.NewsDto;
import com.ouresports.grimstroke.info.entity.News;
import com.ouresports.grimstroke.info.service.CommentService;
import com.ouresports.grimstroke.info.service.NewsService;
import com.ouresports.grimstroke.info.service.UsersInformationService;
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
        News news = generateGeneralQuery();
        news.setId(id);
        NewsDto dto = newsService.getDto(new QueryWrapper<>(news));
        if (getCurrentUser() != null) {
            usersNewsService.addUserBrowsable(getCurrentUser(), newsService.find(id));
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
        Page<CommentDto> page = new Page<>(currentPage, per);
        IPage<CommentDto> commentDtoIPage = commentService.getCommentDtos(page, newsService.find(id), getCurrentUser());
        return render(new PaginationTemplate<>(commentDtoIPage, CommentVo.class));
    }

    /**
     * 评论资讯
     * @param id
     * @param comment
     * @return
     * @throws Exception
     */
    @AuthToken
    @PostMapping(value="/{id}/comments")
    public ResponseEntity addComment(@PathVariable long id,
                                     @Valid @RequestBody CommentRbo comment) throws Exception {
        News news = newsService.find(id);
        commentService.addComment(getCurrentUser(), news, comment.getContent());
        return render(ResultTemplate.createOk());
    }

    /**
     * 增加用户浏览记录
     * @param id
     * @return
     * @throws Exception
     */
    @AuthToken
    @PostMapping(value="/{id}/browse")
    public ResponseEntity browse(@PathVariable long id) throws Exception {
        usersNewsService.addUserBrowsable(getCurrentUser(), newsService.find(id));
        return render(ResultTemplate.createOk());
    }

    private News generateGeneralQuery() {
        return new News().setEnabled(true);
    }
}
