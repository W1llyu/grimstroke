package com.ouresports.grimstroke.im.dto;

import com.ouresports.grimstroke.base.concern.WithUser;
import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.base.entity.User;
import com.ouresports.grimstroke.im.entity.ChatRoomBan;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/12/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChatRoomBanDto extends BaseTo<ChatRoomBan> implements WithUser {
    private Long id;
    private User user;
    private Long userId;
    private Date banTime;
}
