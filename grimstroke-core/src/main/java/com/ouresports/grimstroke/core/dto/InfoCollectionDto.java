package com.ouresports.grimstroke.core.dto;

import lombok.Data;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/11/29
 */
@Data
public class InfoCollectionDto {
    private Long id;
    private String title;
    private String coverImages;
    private Integer gameId;
    private Boolean enabled;
    private Long comentCount;
    private Date createdAt;
}
