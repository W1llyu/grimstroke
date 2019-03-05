package com.ouresports.grimstroke.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/11/22
 */
@Data
public abstract class BaseEntity {
    @TableId(value = "id", type = IdType.AUTO)
    protected Long id;

    @TableField("created_at")
    protected Date createdAt;

    @TableField("updated_at")
    protected Date updatedAt;

    public String getPolymorphicType() {
        return getClass().getSimpleName();
    }
}
