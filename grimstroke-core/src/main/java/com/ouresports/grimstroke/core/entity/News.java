package com.ouresports.grimstroke.core.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.core.base.entity.BaseEntity;
import com.ouresports.grimstroke.core.concern.Commentable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/11/22
 */
@TableName("news")
@Data
@EqualsAndHashCode(callSuper = true)
public class News extends BaseEntity implements Commentable {
    private String title;
    private String coverImage;
    private String content;
    private Integer enabled;

    @TableLogic
    private Date deletedAt;

    @Override
    public String getCommentableType() {
        return this.getClass().getSimpleName();
    }
}
