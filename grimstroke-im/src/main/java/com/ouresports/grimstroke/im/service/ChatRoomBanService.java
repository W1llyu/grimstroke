package com.ouresports.grimstroke.im.service;

import com.ouresports.grimstroke.base.entity.User;
import com.ouresports.grimstroke.base.exception.ServiceException;
import com.ouresports.grimstroke.base.service.Service;
import com.ouresports.grimstroke.im.entity.ChatRoomBan;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/12/22
 */
public interface ChatRoomBanService extends Service<ChatRoomBan> {
    void setUserBanTime(User user, Date banTime);

    void checkUserIsBan(User user) throws ServiceException;

    Date getUserBanTime(User user);
}
