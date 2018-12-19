package com.ouresports.grimstroke.im.rbo;

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
public class GrimConnectMessageRbo {
    private String token;
    private String socketId;
    private Date messageAt;
}
