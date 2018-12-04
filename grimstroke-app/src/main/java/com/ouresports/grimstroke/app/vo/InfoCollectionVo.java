package com.ouresports.grimstroke.app.vo;

import com.ouresports.grimstroke.app.base.vo.BaseVo;
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
public class InfoCollectionVo extends BaseVo<InfoCollectionDto> {
    private Long id;
    private String title;
    private String subTitle;
    private String coverImage;
    private Integer gameId;
    private Boolean sticky;
    private Long newsCount;
    private Long commentCount;
    private Date createdAt;
}
