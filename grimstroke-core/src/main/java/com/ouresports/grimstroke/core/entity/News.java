package com.ouresports.grimstroke.core.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.core.base.entity.BaseEntity;
import com.ouresports.grimstroke.core.concern.Commentable;
import com.ouresports.grimstroke.core.concern.Browsable;
import com.ouresports.grimstroke.core.concern.InfoCollectionable;
import com.ouresports.grimstroke.core.enums.NewsType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/11/22
 */
@TableName("news")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain=true)
public class News extends BaseEntity implements Browsable, Commentable, InfoCollectionable {
    private String title;
    private String coverImages;
    private NewsType type;
    private Integer gameId;
    private String content;
    private Long associateId;
    private Integer enabled;

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
    public String getInfoCollectionableType() {
        return getPolymorphicType();
    }
}
