package com.ouresports.grimstroke.info.rbo.admin;

import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.info.entity.Banner;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author will
 * @date 2018/12/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BannerRbo extends BaseTo<Banner> {
    private String title;
    private String coverImage;
    private String redirectUrl;
    private String resourceType;
    private Long resourceId;
    private Integer priority;
    private String description;
    private Boolean enabled;
}
