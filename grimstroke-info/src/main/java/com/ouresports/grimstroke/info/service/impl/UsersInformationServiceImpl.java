package com.ouresports.grimstroke.info.service.impl;

import com.ouresports.grimstroke.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.info.concern.Browsable;
import com.ouresports.grimstroke.info.entity.User;
import com.ouresports.grimstroke.info.entity.association.UsersInformation;
import com.ouresports.grimstroke.info.mapper.UsersInformationMapper;
import com.ouresports.grimstroke.info.service.UsersInformationService;
import org.springframework.stereotype.Service;

/**
 *
 * @author will
 * @date 2018/11/28
 */
@Service
public class UsersInformationServiceImpl extends BaseServiceImpl<UsersInformationMapper, UsersInformation> implements UsersInformationService {
    @Override
    public void addUserBrowsable(User user, Browsable browsable) {
        UsersInformation condition = new UsersInformation()
                .setUserId(user.getId())
                .setInformationType(browsable.getBrowsableType())
                .setInformationId(browsable.getId());
        findOrCreateBy(condition);
    }
}
