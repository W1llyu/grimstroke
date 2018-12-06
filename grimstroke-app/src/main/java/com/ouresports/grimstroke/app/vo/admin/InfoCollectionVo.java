package com.ouresports.grimstroke.app.vo.admin;

import com.ouresports.grimstroke.core.base.entity.BaseTo;
import com.ouresports.grimstroke.core.dto.InfoCollectionDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/12/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InfoCollectionVo extends BaseTo<InfoCollectionDto> {
    private Long id;
    private String title;
    private String subTitle;
    private String coverImage;
    private Integer gameId;
    private Boolean sticky;
    private Long newsCount;
    private Long commentCount;
    private Boolean enabled;
    private Date createdAt;
}
