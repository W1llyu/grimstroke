package com.ouresports.grimstroke.info.vo.admin;

import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.info.dto.LiveStreamDto;
import com.ouresports.grimstroke.info.enums.LiveStreamType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 * @author will
 * @date 2019/1/5
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LiveStreamVo extends BaseTo<LiveStreamDto> {
    private Long id;
    private LiveStreamType type;
    private String name;
    private Boolean active;
    private Boolean enabled;
    private String description;
    private Long userId;
    private Long matchSeriesId;
    private Date createdAt;
    private Date updatedAt;
}
