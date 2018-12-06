package com.ouresports.grimstroke.app.rbo.admin;

import com.ouresports.grimstroke.core.base.entity.BaseTo;
import com.ouresports.grimstroke.core.entity.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author will
 * @date 2018/12/7
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentRbo extends BaseTo<Comment> {
    private String content;
    private Boolean enabled;
}
