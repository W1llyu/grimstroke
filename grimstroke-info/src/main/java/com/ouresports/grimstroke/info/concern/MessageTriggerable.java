package com.ouresports.grimstroke.info.concern;

import com.ouresports.grimstroke.base.entity.User;

/**
 *
 * @author will
 * @date 2018/12/13
 */
public interface MessageTriggerable {
    String getTriggerableType();

    Long getId();

    Object getTarget();

    User getNotifyUser();
}
