package com.ouresports.grimstroke.core.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.core.base.entity.BaseEntity;
import com.ouresports.grimstroke.core.concern.Commentable;
import com.ouresports.grimstroke.core.concern.Browsable;
import com.ouresports.grimstroke.core.concern.InfoCollectionable;
import com.ouresports.grimstroke.core.enums.InformationSubType;
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
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain=true)
public class News extends BaseEntity implements Browsable, Commentable, InfoCollectionable {
    private String title;
    private String coverImage;
    private InformationSubType type;
    private Integer gameId;
    private Long tagId;
    private String content;
    private String author;
    private String source;
    private Long associateId;
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
    public String getInfoCollectionableType() {
        return getPolymorphicType();
    }
}
