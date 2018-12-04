package com.ouresports.grimstroke.core.dto;

import com.ouresports.grimstroke.core.base.dto.BaseDto;
import com.ouresports.grimstroke.core.entity.Comment;
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
public class SubCommentDto extends BaseDto<Comment> {
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
