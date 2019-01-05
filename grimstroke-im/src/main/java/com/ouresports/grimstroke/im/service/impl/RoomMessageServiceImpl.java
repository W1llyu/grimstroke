package com.ouresports.grimstroke.im.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.base.config.GeneralFastjsonConfig;
import com.ouresports.grimstroke.base.entity.User;
import com.ouresports.grimstroke.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.base.service.UserService;
import com.ouresports.grimstroke.base.util.WrapperUtil;
import com.ouresports.grimstroke.im.dto.RoomMessageDto;
import com.ouresports.grimstroke.im.entity.RoomMessage;
import com.ouresports.grimstroke.im.mapper.RoomMessageMapper;
import com.ouresports.grimstroke.im.rbo.socket.LuxMessageRbo;
import com.ouresports.grimstroke.im.service.NotificationService;
import com.ouresports.grimstroke.im.service.RoomMessageService;
import com.ouresports.grimstroke.im.vo.api.RoomMessageVo;
import com.ouresports.grimstroke.lib.sensiwords.SensitiveWordFilterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author will
 * @date 2018/12/21
 */
@Service
public class RoomMessageServiceImpl extends BaseServiceImpl<RoomMessageMapper, RoomMessage> implements RoomMessageService {
    private static final String ROOMMESSAGE_RECEIVED_EVENT = "grimstroke.chat_room.message.received";
    private static final String ROOMMESSAGE_REMOVED_EVENT = "grimstroke.chat_room.message.removed";
    private static final String ROOMNOTICE_RECEIVED_EVENT = "grimstroke.chat_room.notice.received";
    @Resource
    private NotificationService notificationService;
    @Resource
    private UserService userService;
    @Resource
    private SensitiveWordFilterService sensitiveWordFilterService;

    @Override
    public RoomMessageDto createMessageAndNotify(User user, String roomName, String content) {
        RoomMessage roomMessage = new RoomMessage().setUserId(user.getId()).setRoomName(roomName)
                .setContent(sensitiveWordFilterService.replaceBadWord(content, 2, "*"));
        save(roomMessage);
        RoomMessageDto dto = (RoomMessageDto) new RoomMessageDto().convertFor(roomMessage);
        dto.setUser(user);
        LuxMessageRbo message = new LuxMessageRbo()
                .setChannel(getRoomChannel(roomName))
                .setEvent(ROOMMESSAGE_RECEIVED_EVENT)
                .setData(JSONObject.toJSON(new RoomMessageVo().convertFor(dto), GeneralFastjsonConfig.getFastJsonConfig().getSerializeConfig()));
        notificationService.sendNotification(message);
        return dto;
    }

    @Override
    public IPage<RoomMessageDto> getRoomMessageDtos(IPage<RoomMessage> page, QueryWrapper<RoomMessage> wrapper) {
        wrapper.groupBy(String.format("`%s`.`id`", getTableName()));
        WrapperUtil.appendEqualQuery(wrapper, wrapper.getEntity(), getTableName());
        IPage<RoomMessage> tmpPage = baseMapper.selectPage(page, wrapper);
        List<RoomMessageDto> dtos = tmpPage.getRecords().stream().map(x -> (RoomMessageDto) new RoomMessageDto().convertFor(x)).collect(Collectors.toList());
        userService.includeUsers(dtos);
        includeAdmin(dtos);
        return new Page<RoomMessageDto>(tmpPage.getCurrent(), tmpPage.getSize(), tmpPage.getTotal()).setRecords(dtos);
    }

    @Override
    public RoomMessageDto createAdminMessageAndNotify(String roomName, String content) {
        return createMessageAndNotify(generateAdminUser(), roomName, content);
    }

    @Override
    public void checkFrequency(User user) {
    }

    @Override
    public void createNoticeAndNotify(String roomName, String content) {
        LuxMessageRbo message = new LuxMessageRbo()
                .setChannel(getRoomChannel(roomName))
                .setEvent(ROOMNOTICE_RECEIVED_EVENT)
                .setData(JSONObject.toJSON(new RoomMessageVo().setContent(content), GeneralFastjsonConfig.getFastJsonConfig().getSerializeConfig()));
        notificationService.sendNotification(message);
    }

    @Override
    public void deleteMessageAndNotify(RoomMessage roomMessage) {
        removeById(roomMessage.getId());
        LuxMessageRbo message = new LuxMessageRbo()
                .setChannel(getRoomChannel(roomMessage.getRoomName()))
                .setEvent(ROOMMESSAGE_REMOVED_EVENT)
                .setData(JSONObject.toJSON(roomMessage, GeneralFastjsonConfig.getFastJsonConfig().getSerializeConfig()));
        notificationService.sendNotification(message);
    }

    @Override
    public String getRoomChannel(String roomName) {
        return String.format("grimstroke.chat_room.%s", roomName);
    }

    private void includeAdmin(List<RoomMessageDto> dtos) {
        for (RoomMessageDto dto: dtos) {
            if (dto.getUserId() == 0) {
                dto.setUser(generateAdminUser());
            }
        }
    }

    private User generateAdminUser() {
        User user = new User().setName("超管").setPhone("00000001");
        user.setId(0L);
        return user;
    }
}
