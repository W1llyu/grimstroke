package com.ouresports.grimstroke.core.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.core.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/11/28
 */
@TableName("tags")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain=true)
public class Tag extends BaseEntity {
    private String name;
    private String description;
    private Long parentTagId;
    private Boolean enabled;

    @TableLogic
    private Date deletedAt;
}
