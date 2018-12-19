package com.ouresports.grimstroke.im.rbo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author will
 * @date 2018/12/18
 */
@Data
@Accessors(chain=true)
public class LuxMessageRbo {
    private String channel;
    private String event;
    private Object data;
}
