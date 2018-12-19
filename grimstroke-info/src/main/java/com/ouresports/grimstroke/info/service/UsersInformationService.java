package com.ouresports.grimstroke.info.service;

import com.ouresports.grimstroke.base.service.Service;
import com.ouresports.grimstroke.info.concern.Browsable;
import com.ouresports.grimstroke.info.entity.User;
import com.ouresports.grimstroke.info.entity.association.UsersInformation;

/**
 *
 * @author will
 * @date 2018/11/28
 */
public interface UsersInformationService extends Service<UsersInformation> {
    /**
     * 添加一条用户浏览记录
     * @param user
     * @param readable
     */
    void addUserBrowsable(User user, Browsable readable);
}
