package com.ouresports.grimstroke.app.vo.api;

import com.ouresports.grimstroke.core.base.entity.BaseTo;
import com.ouresports.grimstroke.core.entity.Banner;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author will
 * @date 2018/12/5
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BannerVo extends BaseTo<Banner> {
    private String title;
    private String coverImage;
    private String redirectUrl;
    private String description;
}
