package com.ouresports.grimstroke.im.rbo.socket;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/12/18
 */
@Data
@Accessors(chain=true)
public class GrimRoomCallbackMessageRbo {
    private String credential;
    private String socketId;
    private Date messageAt;
    private String roomName;
    private Long memberCount;

    public void setMessageAt(long messageAt) {
        this.messageAt = new Date(messageAt);
    }
}
