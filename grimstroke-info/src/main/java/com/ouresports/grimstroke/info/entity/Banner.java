package com.ouresports.grimstroke.info.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author will
 * @date 2018/12/5
 */
@TableName("banners")
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain=true)
public class Banner extends BaseEntity {
    private String title;
    private String coverImage;
    private String redirectUrl;
    private String resourceType;
    private Long resourceId;
    private Integer priority;
    private String description;
    private Boolean enabled;
}
