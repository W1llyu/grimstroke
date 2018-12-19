package com.ouresports.grimstroke.info.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author will
 * @date 2018/12/8
 */
@TableName("employees")
@EqualsAndHashCode(callSuper = true)
@Data
public class Employee extends BaseEntity {
    private String name;
    private String phone;
    private String title;
    private String token;
}
