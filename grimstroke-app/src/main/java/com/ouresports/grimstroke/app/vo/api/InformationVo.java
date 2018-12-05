package com.ouresports.grimstroke.app.vo.api;

import com.ouresports.grimstroke.core.base.entity.BaseTo;
import com.ouresports.grimstroke.core.dto.InformationDto;
import com.ouresports.grimstroke.core.enums.InformationSubType;
import com.ouresports.grimstroke.core.enums.InformationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/11/30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InformationVo extends BaseTo<InformationDto> {
    private Long id;
    private InformationType type;
    private InformationSubType subType;
    private String title;
    private String subTitle;
    private String coverImage;
    private Integer gameId;
    private String tagName;
    private Long newsCount;
    private Long commentCount;
    private Boolean sticky;
    private Date createdAt;
}
