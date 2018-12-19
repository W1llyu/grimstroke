package com.ouresports.grimstroke.info.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author will
 * @date 2018/11/28
 */
@TableName("likes")
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain=true)
public class Like extends BaseEntity {
    private Long userId;
    private String targetType;
    private Long targetId;
}
