package com.ouresports.grimstroke.im.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.base.entity.User;
import com.ouresports.grimstroke.base.service.Service;
import com.ouresports.grimstroke.im.dto.RoomMessageDto;
import com.ouresports.grimstroke.im.entity.RoomMessage;

/**
 *
 * @author will
 * @date 2018/12/21
 */
public interface RoomMessageService extends Service<RoomMessage> {
    RoomMessageDto createMessageAndNotify(User user, String roomName, String content);

    IPage<RoomMessageDto> getRoomMessageDtos(IPage<RoomMessage> page, QueryWrapper<RoomMessage> wrapper);

    void deleteMessageAndNotify(RoomMessage message);
}
