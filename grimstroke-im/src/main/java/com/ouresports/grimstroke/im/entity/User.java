package com.ouresports.grimstroke.im.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author will
 * @date 2018/12/18
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
}
