package com.ouresports.grimstroke.im.service.impl;

import com.ouresports.grimstroke.base.exception.ServiceException;
import com.ouresports.grimstroke.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.im.entity.MatchSeries;
import com.ouresports.grimstroke.im.mapper.MatchSeriesMapper;
import com.ouresports.grimstroke.im.service.MatchSeriesService;
import org.springframework.stereotype.Service;

import static com.ouresports.grimstroke.im.enums.ImServiceError.CHATROOM_NOT_OPEN;
import static com.ouresports.grimstroke.im.enums.ImServiceError.CHATROOM_CLOSED;
import static com.ouresports.grimstroke.im.enums.ImServiceError.MATCHSERIES_STATUS_INVALID;
import static com.ouresports.grimstroke.im.enums.MatchSeriesStatus.*;

/**
 *
 * @author will
 * @date 2018/12/18
 */
@Service
public class MatchSeriesServiceImpl extends BaseServiceImpl<MatchSeriesMapper, MatchSeries> implements MatchSeriesService {
    @Override
    public String getRoomName(MatchSeries matchSeries) {
        return String.format("match_series_%d", matchSeries.getId());
    }

    @Override
    public void checkChatRoomOpen(MatchSeries matchSeries) throws ServiceException {
        if (matchSeries.getStatus() == Failed || matchSeries.getStatus() == RoundChanged || matchSeries.getStatus() == Canceled) {
            throw new ServiceException(MATCHSERIES_STATUS_INVALID);
        }
        Long nowTimestamp = System.currentTimeMillis() / 1000;
        if (matchSeries.getStatus() == NotStartYet && nowTimestamp < matchSeries.getStartTime() - 1800) {
            throw new ServiceException(CHATROOM_NOT_OPEN);
        }
        if (matchSeries.getStatus() == Finished && nowTimestamp >= (matchSeries.getUpdatedAt().getTime() / 1000) + 600) {
            throw new ServiceException(CHATROOM_CLOSED);
        }
    }
}
