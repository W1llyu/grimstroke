package com.ouresports.grimstroke.info.dto;

import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.info.entity.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/11/30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SubCommentDto extends BaseTo<Comment> {
    private Long id;
    private Long userId;
    private Long targetUserId;
    private String content;
    private Boolean enabled;
    private Date createdAt;
    private Boolean liked;
    private Long likeCount;
    private NormalUserDto user;
    private NormalUserDto targetUser;
}
