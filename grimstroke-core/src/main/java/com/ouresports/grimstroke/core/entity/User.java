package com.ouresports.grimstroke.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.core.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author will
 * @date 2018/11/22
 */
@TableName("users")
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    private Long externId;
}
