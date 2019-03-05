package com.ouresports.grimstroke.info.rbo.admin;

import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.info.entity.Comment;
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
