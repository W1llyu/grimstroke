package com.ouresports.grimstroke.im.service;

import com.ouresports.grimstroke.base.exception.ServiceException;
import com.ouresports.grimstroke.base.service.Service;
import com.ouresports.grimstroke.im.entity.MatchSeries;

/**
 *
 * @author will
 * @date 2018/12/18
 */
public interface MatchSeriesService extends Service<MatchSeries> {
    boolean chatRoomOpen(MatchSeries matchSeries);

    void checkChatRoomOpen(MatchSeries matchSeries) throws ServiceException;

    String getRoomName(MatchSeries matchSeries);
}
