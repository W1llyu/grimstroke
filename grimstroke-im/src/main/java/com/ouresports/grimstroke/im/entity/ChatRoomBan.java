package com.ouresports.grimstroke.im.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/12/22
 */
@EqualsAndHashCode(callSuper = true)
@TableName("chat_room_bans")
@Data
@Accessors(chain=true)
public class ChatRoomBan extends BaseEntity {
    private Long userId;
    private Date banTime;
}
