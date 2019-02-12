package com.ouresports.grimstroke.im.listener;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ouresports.grimstroke.base.config.GeneralFastjsonConfig;
import com.ouresports.grimstroke.base.entity.User;
import com.ouresports.grimstroke.base.service.UserService;
import com.ouresports.grimstroke.im.enums.UserAction;
import com.ouresports.grimstroke.im.rbo.socket.GrimRoomCallbackMessageRbo;
import com.ouresports.grimstroke.im.rbo.socket.LuxMessageRbo;
import com.ouresports.grimstroke.im.service.NotificationService;
import com.ouresports.grimstroke.im.vo.api.UserActionVo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *
 * @author will
 * @date 2018/12/18
 */
@Component
public class LuxUserActionListener {
    @Resource
    private UserService userService;
    @Resource
    private NotificationService notificationService;

    private static final String ROOM_ACTION_RECEIVED_EVENT = "grimstroke.action.received";

    @RabbitListener(queues="join_room")
    public void processJoinRoom(String msg) {
        try {
            GrimRoomCallbackMessageRbo callbackRbo = JSONObject.parseObject(msg, GrimRoomCallbackMessageRbo.class, GeneralFastjsonConfig.getFastJsonConfig().getFeatures());
            if (needNotify(callbackRbo)) {
                notifyUserAction(callbackRbo, UserAction.JoinRoom);
            }
        } catch (Exception ignored) {}
    }

    @RabbitListener(queues="leave_room")
    public void processLeaveRoom(String msg) {
        try {
            GrimRoomCallbackMessageRbo callbackRbo = JSONObject.parseObject(msg, GrimRoomCallbackMessageRbo.class, GeneralFastjsonConfig.getFastJsonConfig().getFeatures());
            if (needNotify(callbackRbo)) {
                notifyUserAction(callbackRbo, UserAction.LeaveRoom);
            }
        } catch (Exception ignored) {}
    }

    private boolean needNotify(GrimRoomCallbackMessageRbo rbo) {
        return rbo.getRoomName().matches("grimstroke\\..*?");
    }

    private void notifyUserAction(GrimRoomCallbackMessageRbo callbackRbo, UserAction action) {
        UserActionVo vo = new UserActionVo()
                .setAction(action);
        if (callbackRbo.getCredential().isEmpty()) {
            vo.setUser(new User().setName("匿名用户"));
        } else {
            vo.setUser(userService.getUserByToken(callbackRbo.getCredential()));
            if (vo.getUser().getName() == null && vo.getUser().getPhone() == null) {
                vo.getUser().setName("匿名用户");
            }
        }
        LuxMessageRbo message = new LuxMessageRbo()
                .setChannel(callbackRbo.getRoomName())
                .setEvent(ROOM_ACTION_RECEIVED_EVENT)
                .setData(JSONObject.toJSON(vo, GeneralFastjsonConfig.getFastJsonConfig().getSerializeConfig()));
        notificationService.sendNotification(message);
    }
}
