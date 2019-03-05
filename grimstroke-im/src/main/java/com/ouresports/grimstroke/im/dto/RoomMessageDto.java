package com.ouresports.grimstroke.im.dto;

import com.ouresports.grimstroke.base.concern.WithUser;
import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.base.entity.User;
import com.ouresports.grimstroke.im.entity.RoomMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/12/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoomMessageDto extends BaseTo<RoomMessage> implements WithUser {
    private Long id;
    private Long userId;
    private String roomName;
    private String content;
    private Date createdAt;
    private User user;
}
