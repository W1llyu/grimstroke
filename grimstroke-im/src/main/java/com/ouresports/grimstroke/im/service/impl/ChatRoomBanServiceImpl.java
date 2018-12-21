package com.ouresports.grimstroke.im.service.impl;

import com.ouresports.grimstroke.base.entity.User;
import com.ouresports.grimstroke.base.exception.ServiceException;
import com.ouresports.grimstroke.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.im.entity.ChatRoomBan;
import com.ouresports.grimstroke.im.mapper.ChatRoomBanMapper;
import com.ouresports.grimstroke.im.service.ChatRoomBanService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.ouresports.grimstroke.im.enums.ImServiceError.USER_BANED_IN_CHATROOM;

/**
 *
 * @author will
 * @date 2018/12/22
 */
@Service
public class ChatRoomBanServiceImpl extends BaseServiceImpl<ChatRoomBanMapper, ChatRoomBan> implements ChatRoomBanService {
    @Override
    public void setUserBanTime(User user, Date banTime) {
        ChatRoomBan query = new ChatRoomBan().setUserId(user.getId());
        ChatRoomBan chatRoomBan = findBy(query);
        if (chatRoomBan == null) {
            query.setBanTime(banTime);
            save(query);
        } else {
            chatRoomBan.setBanTime(banTime);
            updateById(chatRoomBan);
        }
    }

    @Override
    public void checkUserIsBan(User user) throws ServiceException {
        Date banTime = getUserBanTime(user);
        if (banTime == null) {
            return;
        }
        if (System.currentTimeMillis() < banTime.getTime()) {
            throw new ServiceException(USER_BANED_IN_CHATROOM.value(), String.format("用户已被禁言至%s",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(banTime)));
        }
    }

    @Override
    public Date getUserBanTime(User user) {
        ChatRoomBan chatRoomBan = findBy(new ChatRoomBan().setUserId(user.getId()));
        if (chatRoomBan == null) {
            return null;
        }
        return chatRoomBan.getBanTime();
    }
}
