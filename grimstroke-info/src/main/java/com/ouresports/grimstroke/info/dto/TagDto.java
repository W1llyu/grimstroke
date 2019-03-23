package com.ouresports.grimstroke.info.dto;

import com.ouresports.grimstroke.info.entity.Tag;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 *
 * @author will
 * @date 2018/12/10
 */
@Data
public class TagDto {
    private Long id;
    private String name;
    private String description;
    private Long parentTagId;
    private String parentTagName;
    private Boolean enabled;
    private Date createdAt;
    private List<Tag> children;
}
