package com.ouresports.grimstroke.im.vo.admin;

import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.base.util.SpringUtil;
import com.ouresports.grimstroke.im.dto.MatchSeriesDto;
import com.ouresports.grimstroke.im.entity.MatchSeries;
import com.ouresports.grimstroke.im.enums.MatchSeriesStatus;
import com.ouresports.grimstroke.im.service.MatchSeriesService;
import com.ouresports.grimstroke.im.service.RoomMessageService;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 * @author will
 * @date 2019/1/7
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SeriesChatRoomVo extends BaseTo<MatchSeriesDto> {
    private Long id;
    private String leftTag;
    private String rightTag;
    private String leagueName;
    private Date startTime;
    private MatchSeriesStatus status;
    private String roomName;
    private Integer gameId;
    private Long userCount;

    public void setStartTime(Long startTime) {
        this.startTime = new Date(startTime * 1000);
    }

    public String getRoomName() {
        if (roomName == null) {
            MatchSeries m = new MatchSeries();
            m.setId(id);
            roomName = SpringUtil.getBean(MatchSeriesService.class).getRoomName(m);
        }
        return roomName;
    }

    public Long getUserCount() {
        if (userCount == null) {
            userCount = SpringUtil.getBean(RoomMessageService.class).getRoomUserCount(getRoomName());
        }
        return userCount;
    }
}
