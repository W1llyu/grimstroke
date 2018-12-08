package com.ouresports.grimstroke.app.controller.admin;

import com.ouresports.grimstroke.app.base.annotation.*;
import com.ouresports.grimstroke.app.rbo.admin.CommentRbo;
import com.ouresports.grimstroke.core.entity.Comment;
import com.ouresports.grimstroke.core.service.CommentService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author will
 * @date 2018/12/7
 */
@RestCrudController(value="AdminCommentController")
@RequestMapping(value="/admin/comments", produces="application/json;charset=UTF-8")
@RestIndex
@RestUpdate(rboClass=CommentRbo.class, valid=false)
public class CommentController extends BaseController<Comment, CommentService> {
}
