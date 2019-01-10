package com.ouresports.grimstroke.im.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ouresports.grimstroke.base.config.GeneralFastjsonConfig;
import com.ouresports.grimstroke.im.rbo.socket.LuxMessageRbo;
import com.ouresports.grimstroke.im.service.ChatRoomService;
import com.ouresports.grimstroke.im.service.NotificationService;
import com.ouresports.grimstroke.im.vo.api.RoomMessageVo;
import com.ouresports.grimstroke.im.vo.api.RoomRewardVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @author will
 * @date 2019/1/10
 */
@Service
public class ChatRoomServiceImpl implements ChatRoomService {
    private static final String ROOMNOTICE_RECEIVED_EVENT = "grimstroke.chat_room.notice.received";
    private static final String ROOMREWARD_RECEIVED_EVENT = "grimstroke.chat_room.reward.received";
    @Resource
    private NotificationService notificationService;

    @Override
    public void createNoticeAndNotify(String roomName, String content) {
        LuxMessageRbo message = new LuxMessageRbo()
                .setChannel(getRoomChannel(roomName))
                .setEvent(ROOMNOTICE_RECEIVED_EVENT)
                .setData(JSONObject.toJSON(new RoomMessageVo().setContent(content), GeneralFastjsonConfig.getFastJsonConfig().getSerializeConfig()));
        notificationService.sendNotification(message);
    }

    @Override
    public void rewardNotify(String roomName, RoomRewardVo vo) {
        LuxMessageRbo message = new LuxMessageRbo()
                .setChannel(getRoomChannel(roomName))
                .setEvent(ROOMREWARD_RECEIVED_EVENT)
                .setData(JSONObject.toJSON(vo, GeneralFastjsonConfig.getFastJsonConfig().getSerializeConfig()));
        notificationService.sendNotification(message);
    }

    @Override
    public String getRoomChannel(String roomName) {
        return String.format("grimstroke.chat_room.%s", roomName);
    }
}
