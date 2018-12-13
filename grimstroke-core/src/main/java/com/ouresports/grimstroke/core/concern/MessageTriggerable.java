package com.ouresports.grimstroke.core.concern;

import com.ouresports.grimstroke.core.entity.User;

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
