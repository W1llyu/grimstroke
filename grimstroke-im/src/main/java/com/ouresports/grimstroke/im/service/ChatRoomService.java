package com.ouresports.grimstroke.im.service;

import com.ouresports.grimstroke.im.vo.api.RoomRewardVo;

/**
 *
 * @author will
 * @date 2019/1/10
 */
public interface ChatRoomService {
    void createNoticeAndNotify(String roomName, String content);

    void rewardNotify(String roomName, RoomRewardVo vo);

    String getRoomChannel(String roomName);
}
