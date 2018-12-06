package com.ouresports.grimstroke.app.rbo.admin;

import com.ouresports.grimstroke.core.base.entity.BaseTo;
import com.ouresports.grimstroke.core.entity.InfoCollection;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 *
 * @author will
 * @date 2018/12/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InfoCollectionRbo extends BaseTo<InfoCollection> {
    @NotNull(message="标题不可为空")
    private String title;
    @NotNull(message="副标题不可为空")
    private String subTitle;
    @NotNull(message="封面图片不可为空")
    private String coverImage;
    private Integer gameId;
    private Boolean sticky;
    private Boolean enabled;
}
