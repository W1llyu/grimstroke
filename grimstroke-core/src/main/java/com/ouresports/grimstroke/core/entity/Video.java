package com.ouresports.grimstroke.core.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.core.base.entity.BaseEntity;
import com.ouresports.grimstroke.core.concern.Commentable;
import com.ouresports.grimstroke.core.concern.Browsable;
import com.ouresports.grimstroke.core.concern.Likable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 视频
 * @author will
 * @date 2018/11/29
 */
@TableName("users_news")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain=true)
public class Video extends BaseEntity implements Browsable, Commentable, Likable {
    private String title;
    private String coverImage;
    private String address;
    private Integer gameId;
    private Long tagId;
    private Boolean sticky;
    private Boolean enabled;

    @TableLogic
    private Date deletedAt;

    @Override
    public String getCommentableType() {
        return getPolymorphicType();
    }

    @Override
    public String getBrowsableType() {
        return getPolymorphicType();
    }

    @Override
    public String getLikableType() {
        return getPolymorphicType();
    }
}
