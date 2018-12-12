package com.ouresports.grimstroke.app.rbo.admin;

import com.ouresports.grimstroke.core.base.entity.BaseTo;
import com.ouresports.grimstroke.core.entity.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author will
 * @date 2018/12/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TagRbo extends BaseTo<Tag> {
    @NotNull(message="名字不能为空")
    @NotBlank(message="名字不可为空")
    private String name;
    private String description;
    private Long parentTagId;
    private Boolean enabled;
}
