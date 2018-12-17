package com.ouresports.grimstroke.info.controller.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.base.annotation.AuthToken;
import com.ouresports.grimstroke.base.template.PaginationTemplate;
import com.ouresports.grimstroke.base.template.ResultTemplate;
import com.ouresports.grimstroke.base.template.SingleTemplate;
import com.ouresports.grimstroke.info.rbo.api.CommentRbo;
import com.ouresports.grimstroke.info.vo.api.CommentVo;
import com.ouresports.grimstroke.info.vo.api.SubCommentVo;
import com.ouresports.grimstroke.info.dto.CommentDto;
import com.ouresports.grimstroke.info.dto.SubCommentDto;
import com.ouresports.grimstroke.info.service.CommentService;
import com.ouresports.grimstroke.info.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *
 * @author will
 * @date 2018/11/30
 */
@RestController
@RequestMapping(value="/api/comments", produces="application/json;charset=UTF-8")
public class CommentController extends BaseController {
    @Resource
    private CommentService commentService;
    @Resource
    private LikeService likeService;

    /**
     * 获得评论详情
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{id}")
    public ResponseEntity show(@PathVariable long id) throws Exception {
        CommentDto commentDto = commentService.getCommentDto(id, getCurrentUser());
        return render(new SingleTemplate<>(commentDto, CommentVo.class));
    }

    /**
     * 获取评论的回复
     * @param id
     * @param currentPage
     * @param per
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{id}/sub_comments")
    public ResponseEntity subComments(@PathVariable long id,
                                      @RequestParam(value="page", defaultValue="1") int currentPage,
                                      @RequestParam(defaultValue="10") int per) throws Exception {
        Page<SubCommentDto> page = new Page<>(currentPage, per);
        IPage<SubCommentDto> dtos = commentService.getSubCommentDtos(page, commentService.find(id), getCurrentUser());
        return render(new PaginationTemplate<>(dtos, SubCommentVo.class));
    }

    /**
     * 用户点赞一条评论
     * @param id
     * @return
     * @throws Exception
     */
    @AuthToken
    @PostMapping(value="/{id}/like")
    public ResponseEntity likeComment(@PathVariable long id) throws Exception {
        likeService.addLike(getCurrentUser(), commentService.find(id));
        return render(ResultTemplate.createOk());
    }

    /**
     * 取消点赞一条评论
     * @param id
     * @return
     * @throws Exception
     */
    @AuthToken
    @DeleteMapping(value="/{id}/remove_like")
    public ResponseEntity removeLikeComment(@PathVariable long id) throws Exception {
        likeService.removeLike(getCurrentUser(), commentService.find(id));
        return render(ResultTemplate.deleteOk());
    }

    /**
     * 回复评论
     * @param id
     * @param commentRbo
     * @return
     * @throws Exception
     */
    @AuthToken
    @PostMapping(value="/{id}/comments")
    public ResponseEntity addComment(@PathVariable long id,
                                     @Valid @RequestBody CommentRbo commentRbo) throws Exception {
        commentService.addComment(getCurrentUser(), commentService.find(id), commentRbo.getContent());
        return render(ResultTemplate.createOk());
    }
}
