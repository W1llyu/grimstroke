package com.ouresports.grimstroke.info.dto;

import com.ouresports.grimstroke.base.concern.WithUser;
import com.ouresports.grimstroke.base.entity.User;
import com.ouresports.grimstroke.info.enums.LiveStreamType;
import lombok.Data;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/12/20
 */
@Data
public class LiveStreamDto implements WithUser {
    private Long id;
    private LiveStreamType type;
    private String name;
    private Boolean active;
    private Boolean enabled;
    private String description;
    private Long userId;
    private User user;
    private Long matchSeriesId;
    private Date createdAt;
    private Date updatedAt;
}
