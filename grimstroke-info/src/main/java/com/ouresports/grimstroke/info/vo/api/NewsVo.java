package com.ouresports.grimstroke.info.vo.api;

import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.info.dto.NewsDto;
import com.ouresports.grimstroke.info.enums.InformationSubType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/11/27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain=true)
public class NewsVo extends BaseTo<NewsDto> {
    private Long id;
    private String title;
    private InformationSubType type;
    private Integer gameId;
    private String tagName;
    private String content;
    private String author;
    private String source;
    private Long commentCount;
    private Boolean sticky;
    private Long associateId;
    private String coverImage;
    private Date createdAt;
}
