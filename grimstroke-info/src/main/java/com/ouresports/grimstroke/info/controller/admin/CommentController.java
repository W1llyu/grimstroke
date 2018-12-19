package com.ouresports.grimstroke.info.controller.admin;

import com.ouresports.grimstroke.base.annotation.*;
import com.ouresports.grimstroke.info.rbo.admin.CommentRbo;
import com.ouresports.grimstroke.info.vo.admin.CommentVo;
import com.ouresports.grimstroke.info.entity.Comment;
import com.ouresports.grimstroke.info.service.CommentService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author will
 * @date 2018/12/7
 */
@RestCrudController(value="AdminCommentController")
@RequestMapping(value="/admin/comments", produces="application/json;charset=UTF-8")
@RestIndex(voClass=CommentVo.class)
@RestShow(voClass=CommentVo.class)
@RestUpdate(rboClass=CommentRbo.class, valid=false)
public class CommentController extends BaseController<Comment, CommentService> {
}
