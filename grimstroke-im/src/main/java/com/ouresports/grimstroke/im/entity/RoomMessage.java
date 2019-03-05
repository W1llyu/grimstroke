package com.ouresports.grimstroke.im.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author will
 * @date 2018/12/21
 */
@TableName("room_messages")
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain=true)
public class RoomMessage extends BaseEntity {
    private Long userId;
    private String roomName;
    private String content;
}
