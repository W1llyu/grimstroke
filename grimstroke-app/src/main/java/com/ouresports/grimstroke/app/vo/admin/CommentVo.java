package com.ouresports.grimstroke.app.vo.admin;

import com.ouresports.grimstroke.core.base.entity.BaseTo;
import com.ouresports.grimstroke.core.entity.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/12/10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentVo extends BaseTo<Comment> {
    private Long id;
    private Date createdAt;
    private Long userId;
    private String rootType;
    private Long rootId;
    private Long rootCommentId;
    private Long parentCommentId;
    private String content;
    private Boolean enabled;
}
