package com.ouresports.grimstroke.core.dto;

import com.ouresports.grimstroke.core.enums.NewsType;
import lombok.Data;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/11/29
 */
@Data
public class NewsDto {
    private Long id;
    private String title;
    private String content;
    private Long commentCount;
    private NewsType type;
    private Integer gameId;
    private String coverImages;
    private Date createdAt;
}
