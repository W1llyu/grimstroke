package com.ouresports.grimstroke.info.vo.api;

import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.info.entity.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by will on 2019/3/15.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain=true)
public class TagVo extends BaseTo<Tag> {
    private Long id;
    private String name;
}
