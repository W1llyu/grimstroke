package com.ouresports.grimstroke.info.vo.api;

import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.base.entity.ITo;
import com.ouresports.grimstroke.info.dto.CommentDto;
import com.ouresports.grimstroke.info.dto.NormalUserDto;
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
public class CommentVo extends BaseTo<CommentDto> {
    private Long id;
    private String content;
    private Long subCommentCount;
    private Date createdAt;
    private List subComments;
    private Long likeCount;
    private Boolean liked;
    private UserVo user;

    @Override
    public ITo<CommentDto> convertFor(CommentDto commentDto) {
        CommentVo vo = (CommentVo) super.convertFor(commentDto);
        vo.setSubComments(new SubCommentVo().convertFor(commentDto.getSubComments()));
        return vo;
    }

    public void setUser(NormalUserDto user) {
        if (user != null) {
            this.user = (UserVo) new UserVo().convertFor(user);
        }
    }
}
