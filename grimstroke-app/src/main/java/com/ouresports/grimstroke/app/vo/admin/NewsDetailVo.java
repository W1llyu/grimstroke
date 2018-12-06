package com.ouresports.grimstroke.app.vo.admin;

import com.ouresports.grimstroke.core.base.entity.BaseTo;
import com.ouresports.grimstroke.core.dto.NewsDto;
import com.ouresports.grimstroke.core.enums.InformationSubType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

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
}
