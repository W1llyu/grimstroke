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
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain=true)
public class InfoCollection extends BaseEntity {
    private String title;
    private String coverImages;
    private Integer gameId;
    private Boolean enabled;

    @TableLogic
    private Date deletedAt;
}
