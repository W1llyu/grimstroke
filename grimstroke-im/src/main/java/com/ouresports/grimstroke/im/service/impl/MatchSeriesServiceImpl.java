package com.ouresports.grimstroke.im.service.impl;

import com.google.common.collect.Sets;
import com.ouresports.grimstroke.base.exception.ServiceException;
import com.ouresports.grimstroke.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.im.entity.MatchSeries;
import com.ouresports.grimstroke.im.enums.MatchSeriesStatus;
import com.ouresports.grimstroke.im.mapper.MatchSeriesMapper;
import com.ouresports.grimstroke.im.service.MatchSeriesService;
import org.springframework.stereotype.Service;

import java.util.Set;

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
    private static Set<MatchSeriesStatus> validStatus = Sets.newHashSet(NotStartYet, Ongoing, Finished);

    @Override
    public String getRoomName(MatchSeries matchSeries) {
        return String.format("match_series_%d", matchSeries.getId());
    }

    @Override
    public boolean chatRoomOpen(MatchSeries matchSeries) {
        return chatStatusValid(matchSeries) && !chatRoomNotStart(matchSeries) && !chatRoomAlreadyFinished(matchSeries);
    }

    @Override
    public void checkChatRoomOpen(MatchSeries matchSeries) throws ServiceException {
        if (!chatStatusValid(matchSeries)) {
            throw new ServiceException(MATCHSERIES_STATUS_INVALID);
        }
        if (chatRoomNotStart(matchSeries)) {
            throw new ServiceException(CHATROOM_NOT_OPEN);
        }
        if (chatRoomAlreadyFinished(matchSeries)) {
            throw new ServiceException(CHATROOM_CLOSED);
        }
    }

    private boolean chatStatusValid(MatchSeries matchSeries) {
        return validStatus.contains(matchSeries.getStatus());
    }

    private boolean chatRoomNotStart(MatchSeries matchSeries) {
        Long nowTimestamp = System.currentTimeMillis() / 1000;
        return matchSeries.getStatus() == NotStartYet && nowTimestamp < matchSeries.getStartTime() - 1800;
    }

    private boolean chatRoomAlreadyFinished(MatchSeries matchSeries) {
        Long nowTimestamp = System.currentTimeMillis() / 1000;
        return matchSeries.getStatus() == Finished && nowTimestamp >= (matchSeries.getUpdatedAt().getTime() / 1000) + 8 * 3600 + 600;
    }
}
