package com.ouresports.grimstroke.app.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.app.base.template.PaginationTemplate;
import com.ouresports.grimstroke.app.base.template.ResultTemplate;
import com.ouresports.grimstroke.app.rbo.admin.CommentRbo;
import com.ouresports.grimstroke.core.entity.Comment;
import com.ouresports.grimstroke.core.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *
 * @author will
 * @date 2018/12/7
 */
@RestController(value="AdminCommentController")
@RequestMapping(value="/admin/comments", produces="application/json;charset=UTF-8")
public class CommentController extends BaseController {
    @Resource
    private CommentService commentService;

    /**
     * 列表
     * @param currentPage
     * @param per
     * @param rootType
     * @param rootId
     * @return
     * @throws Exception
     */
    @GetMapping(value="")
    public ResponseEntity index(@RequestParam(value="page", defaultValue="1") int currentPage,
                                @RequestParam(defaultValue="10") int per,
                                @RequestParam(value="root_type", required=false) String rootType,
                                @RequestParam(value="root_id", required=false) Long rootId) throws Exception {
        Comment comment = new Comment().setRootType(rootType).setRootId(rootId);
        Page<Comment> page = new Page<>(currentPage, per);
        QueryWrapper<Comment> wrapper = new QueryWrapper<>(comment).orderByDesc("created_at");
        IPage<Comment> comments = commentService.list(page, wrapper);
        return render(new PaginationTemplate<>(comments));
    }

    /**
     * 更新
     * @param id
     * @param rbo
     * @return
     * @throws Exception
     */
    @PatchMapping(value="/{id}")
    public ResponseEntity update(@PathVariable long id,
                                 @RequestBody CommentRbo rbo) throws Exception {
        Comment comment = rbo.convertTo();
        comment.setId(id);
        commentService.updateById(comment);
        return render(ResultTemplate.updateOk());
    }
}
