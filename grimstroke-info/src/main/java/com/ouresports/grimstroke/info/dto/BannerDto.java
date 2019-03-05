package com.ouresports.grimstroke.info.dto;

import lombok.Data;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/12/6
 */
@Data
public class BannerDto {
    private Long id;
    private String title;
    private String coverImage;
    private String redirectUrl;
    private BannerResourceDto resource;
    private Integer priority;
    private String description;
    private Boolean enabled;
    private Date createdAt;
    private Date updatedAt;
}
