package com.ouresports.grimstroke.app.vo.admin;

import com.ouresports.grimstroke.core.base.entity.BaseTo;
import com.ouresports.grimstroke.core.dto.VideoDto;
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
public class VideoVo extends BaseTo<VideoDto> {
    private Long id;
    private Date createdAt;
    private String title;
    private String coverImage;
    private String vodId;
    private Float duration;
    private Long size;
    private Integer gameId;
    private String tagName;
    private Long tagId;
    private Boolean sticky;
    private Long commentCount;
    private Long likeCount;
    private Boolean enabled;
}
