package com.ouresports.grimstroke.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author will
 * @date 2018/11/22
 */
@TableName("users")
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain=true)
public class User extends BaseEntity {
    private String name;
    private String token;
    private String avatar;
    private String phone;
    private String type;
    private String description;
}
