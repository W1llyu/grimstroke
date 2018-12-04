package com.ouresports.grimstroke.core.dto;

import com.ouresports.grimstroke.core.enums.InformationSubType;
import com.ouresports.grimstroke.core.enums.InformationType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 全部信息流DTO
 * @author will
 * @date 2018/11/29
 */
@Data
@Accessors(chain=true)
public class InformationDto {
    private Long id;
    private InformationType type;
    private InformationSubType subType;
    private String title;
    private String subTitle;
    private String coverImage;
    private Integer gameId;
    private String tagName;
    private Long newsCount;
    private Long browseCount;
    private Long commentCount;
    private Boolean sticky;
    private Boolean enabled;
    private Date createdAt;
}
