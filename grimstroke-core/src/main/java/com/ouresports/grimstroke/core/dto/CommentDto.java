package com.ouresports.grimstroke.core.dto;

import com.ouresports.grimstroke.core.base.entity.BaseTo;
import com.ouresports.grimstroke.core.entity.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/29
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentDto extends BaseTo<Comment> {
    private Long id;
    private Long userId;
    private String rootType;
    private Long rootId;
    private String content;
    private Boolean enabled;
    private Long subCommentCount;
    private List<SubCommentDto> subComments;
    private Boolean liked;
    private Long likeCount;
    private Date createdAt;
    private NormalUserDto user;
}
