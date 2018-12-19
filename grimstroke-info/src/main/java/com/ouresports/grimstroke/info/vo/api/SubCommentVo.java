package com.ouresports.grimstroke.info.vo.api;

import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.info.dto.NormalUserDto;
import com.ouresports.grimstroke.info.dto.SubCommentDto;
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
    private UserVo user;
    private UserVo targetUser;
    private Long likeCount;
    private Boolean liked;
    private Date createdAt;

    public void setUser(NormalUserDto user) {
        if (user != null) {
            this.user = (UserVo) new UserVo().convertFor(user);
        }
    }

    public void setTargetUser(NormalUserDto user) {
        if (user != null) {
            this.targetUser = (UserVo) new UserVo().convertFor(user);
        }
    }
}
