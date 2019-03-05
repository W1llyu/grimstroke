package com.ouresports.grimstroke.info.vo.admin;

import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.info.dto.NewsDto;
import com.ouresports.grimstroke.info.enums.InformationSubType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 *
 * @author will
 * @date 2018/12/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NewsDetailVo extends BaseTo<NewsDto> {
    private Long id;
    private String title;
    private InformationSubType type;
    private Integer gameId;
    private Long tagId;
    private String tagName;
    private String author;
    private String source;
    private String content;
    private Long browseCount;
    private Long commentCount;
    private Boolean sticky;
    private Long associateId;
    private String coverImage;
    private Date createdAt;
    private List<Long> seriesIds;
    private List<Long> teamIds;
}
