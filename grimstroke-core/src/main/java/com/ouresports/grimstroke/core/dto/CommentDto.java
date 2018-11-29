package com.ouresports.grimstroke.core.dto;

import lombok.Data;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/11/29
 */
@Data
public class CommentDto {
    private Long id;
    private String rootType;
    private String rootId;
    private String targetType;
    private String targetId;
    private String content;
    private Boolean enabled;
    private Date createdAt;
}
