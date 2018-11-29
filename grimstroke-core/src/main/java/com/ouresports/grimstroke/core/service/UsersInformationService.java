package com.ouresports.grimstroke.core.service;

import com.ouresports.grimstroke.core.base.service.Service;
import com.ouresports.grimstroke.core.concern.Browsable;
import com.ouresports.grimstroke.core.entity.User;
import com.ouresports.grimstroke.core.entity.association.UsersInformation;

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
    void addUserReadable(User user, Browsable readable);
}
