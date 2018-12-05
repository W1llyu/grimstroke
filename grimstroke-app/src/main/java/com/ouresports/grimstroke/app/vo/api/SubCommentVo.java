package com.ouresports.grimstroke.app.vo.api;

import com.ouresports.grimstroke.core.base.entity.BaseTo;
import com.ouresports.grimstroke.core.dto.NormalUserDto;
import com.ouresports.grimstroke.core.dto.SubCommentDto;
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
public class SubCommentVo extends BaseTo<SubCommentDto> {
    private Long id;
    private String content;
    private NormalUserDto user;
    private NormalUserDto targetUser;
    private Long likeCount;
    private Boolean liked;
    private Date createdAt;
}
