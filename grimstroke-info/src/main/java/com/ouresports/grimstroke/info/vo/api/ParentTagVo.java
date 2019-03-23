package com.ouresports.grimstroke.info.vo.api;

import com.google.common.collect.Lists;
import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.info.dto.TagDto;
import com.ouresports.grimstroke.info.entity.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by will on 2019/3/15.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ParentTagVo extends BaseTo<TagDto> {
    private Long id;
    private String name;
    private List<TagVo> children;

    public void setChildren(List<Tag> tags) {
        children = Lists.newArrayList();
        for (Tag tag: tags) {
            children.add(new TagVo().setId(tag.getId()).setName(tag.getName()));
        }
    }
}
