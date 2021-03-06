package com.ouresports.grimstroke.info.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.base.entity.BaseEntity;
import com.ouresports.grimstroke.info.concern.Commentable;
import com.ouresports.grimstroke.info.concern.Likable;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/11/27
 */
@TableName("comments")
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class Comment extends BaseEntity implements Commentable, Likable {
    private Long userId;
    private String rootType;
    private Long rootId;
    private Long rootCommentId;
    private Long parentCommentId;
    private String content;
    private Boolean enabled;
    @TableLogic
    private Date deletedAt;

    @Override
    public String getCommentableType() {
        return getPolymorphicType();
    }

    @Override
    public String getLikableType() {
        return getPolymorphicType();
    }
}
