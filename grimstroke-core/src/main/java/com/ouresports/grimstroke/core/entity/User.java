package com.ouresports.grimstroke.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.core.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author will
 * @date 2018/11/22
 */
@TableName("users")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain=true)
public class User extends BaseEntity {
    private String name;
    private String token;
    private String avatar;
    private String phone;
    private String type;
}
