package com.ouresports.grimstroke.core.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.core.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 专题合集
 * @author will
 * @date 2018/11/29
 */
@TableName("info_collections")
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain=true)
public class InfoCollection extends BaseEntity {
    private String title;
    private String subTitle;
    private String coverImage;
    private Integer gameId;
    private Boolean sticky;
    private Boolean enabled;

    @TableLogic
    private Date deletedAt;
}
