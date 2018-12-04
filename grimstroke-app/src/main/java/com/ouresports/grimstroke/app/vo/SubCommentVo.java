package com.ouresports.grimstroke.app.vo;

import com.ouresports.grimstroke.app.base.vo.BaseVo;
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
public class SubCommentVo extends BaseVo<SubCommentDto> {
    private Long id;
    private String content;
    private NormalUserDto user;
    private NormalUserDto targetUser;
    private Long likeCount;
    private Boolean liked;
    private Date createdAt;
}
