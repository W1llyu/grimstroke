package com.ouresports.grimstroke.core.entity;

import lombok.Data;
import java.util.Date;

/**
 *
 * @author will
 * @date 2018/11/22
 */
@Data
public abstract class BaseEntity {
    protected Long id;

    protected Date createdAt;

    protected Date updatedAt;
}
