package com.ouresports.grimstroke.app.vo;

import com.ouresports.grimstroke.app.base.vo.BaseVo;
import com.ouresports.grimstroke.app.base.vo.IVo;
import com.ouresports.grimstroke.core.dto.CommentDto;
import com.ouresports.grimstroke.core.dto.NormalUserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentVo extends BaseVo<CommentDto> {
    private Long id;
    private String content;
    private Long subCommentCount;
    private Date createdAt;
    private List subComments;
    private Long likeCount;
    private Boolean liked;
    private NormalUserDto user;

    @Override
    public IVo<CommentDto> convertFor(CommentDto commentDto) {
        CommentVo vo = (CommentVo) super.convertFor(commentDto);
        vo.setSubComments(new SubCommentVo().convertFor(commentDto.getSubComments()));
        return vo;
    }
}
