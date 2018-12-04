package com.ouresports.grimstroke.core.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.core.base.entity.BaseEntity;
import com.ouresports.grimstroke.core.concern.Commentable;
import com.ouresports.grimstroke.core.concern.Likable;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/11/27
 */
@TableName("comments")
@Data
@EqualsAndHashCode(callSuper = true)
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
