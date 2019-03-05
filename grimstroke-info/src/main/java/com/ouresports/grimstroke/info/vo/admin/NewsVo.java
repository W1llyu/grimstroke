package com.ouresports.grimstroke.info.vo.admin;

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
    private Long tagId;
    private String tagName;
    private String author;
    private String source;
    private Long browseCount;
    private Long commentCount;
    private Boolean sticky;
    private Boolean enabled;
    private Long associateId;
    private Long seriesId;
    private String coverImage;
    private Date createdAt;
}
