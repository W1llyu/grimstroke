package com.ouresports.grimstroke.im.vo.api;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author will
 * @date 2019/1/4
 */
@Data
@Accessors(chain=true)
public class RoomInfoVo {
    private String name;
    private String channel;
    private Boolean open;
}
