package com.ouresports.grimstroke.info.dto;

import com.ouresports.grimstroke.base.concern.Userable;
import com.ouresports.grimstroke.base.entity.User;
import com.ouresports.grimstroke.info.enums.LiveStreamType;
import lombok.Data;

/**
 *
 * @author will
 * @date 2018/12/20
 */
@Data
public class LiveStreamDto implements Userable {
    private Long id;
    private LiveStreamType type;
    private String name;
    private Boolean active;
    private Long userId;
    private User user;
}
