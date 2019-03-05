package com.ouresports.grimstroke.info.dto;

import lombok.Data;

import java.util.Date;

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
}
