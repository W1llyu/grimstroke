package com.ouresports.grimstroke.app.controller.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.app.base.template.PaginationTemplate;
import com.ouresports.grimstroke.app.base.template.ResultTemplate;
import com.ouresports.grimstroke.app.base.template.SingleTemplate;
import com.ouresports.grimstroke.app.rbo.api.CommentRbo;
import com.ouresports.grimstroke.app.vo.api.CommentVo;
import com.ouresports.grimstroke.app.vo.api.SubCommentVo;
import com.ouresports.grimstroke.core.dto.CommentDto;
import com.ouresports.grimstroke.core.dto.SubCommentDto;
import com.ouresports.grimstroke.core.entity.Comment;
import com.ouresports.grimstroke.core.service.CommentService;
import com.ouresports.grimstroke.core.service.LikeService;
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
        authenticateUser();
        CommentDto commentDto = commentService.getCommentDto(id, currentUser);
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
        authenticateUser();
        Comment comment = loadCommentById(id);
        Page<SubCommentDto> page = new Page<>(currentPage, per);
        IPage<SubCommentDto> dtos = commentService.getSubCommentDtoPage(page, comment, currentUser);
        return render(new PaginationTemplate<>(dtos, SubCommentVo.class));
    }

    /**
     * 用户点赞一条评论
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping(value="/{id}/like")
    public ResponseEntity likeComment(@PathVariable long id) throws Exception {
        authenticateUserForce();
        likeService.addLike(currentUser, loadCommentById(id));
        return render(ResultTemplate.createOk());
    }

    /**
     * 取消点赞一条评论
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping(value="/{id}/remove_like")
    public ResponseEntity removeLikeComment(@PathVariable long id) throws Exception {
        authenticateUserForce();
        likeService.removeLike(currentUser, loadCommentById(id));
        return render(ResultTemplate.deleteOk());
    }

    /**
     * 回复评论
     * @param id
     * @param commentRbo
     * @return
     * @throws Exception
     */
    @PostMapping(value="/{id}/comments")
    public ResponseEntity addComment(@PathVariable long id,
                                     @Valid @RequestBody CommentRbo commentRbo) throws Exception {
        authenticateUserForce();
        commentService.addComment(currentUser, loadCommentById(id), commentRbo.getContent());
        return render(ResultTemplate.createOk());
    }

    private Comment loadCommentById(long id) throws Exception {
        return commentService.find(id);
    }
}
