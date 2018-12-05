package com.ouresports.grimstroke.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.core.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author will
 * @date 2018/12/5
 */
@TableName("banners")
@EqualsAndHashCode(callSuper = true)
@Data
public class Banner extends BaseEntity {
    private String title;
    private String coverImage;
    private String redirectUrl;
    private Integer priority;
    private String description;
    private Boolean enabled;
}
