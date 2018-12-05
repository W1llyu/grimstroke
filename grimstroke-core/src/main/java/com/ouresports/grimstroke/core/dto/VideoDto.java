package com.ouresports.grimstroke.core.dto;

import lombok.Data;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/12/5
 */
@Data
public class VideoDto {
    private Long id;
    private Date createdAt;
    private String title;
    private String coverImage;
    private String vodId;
    private Float duration;
    private Long size;
    private Integer gameId;
    private String tagName;
    private Boolean sticky;
    private Long commentCount;
    private Long likeCount;
    private Boolean enabled;
}
