package com.ouresports.grimstroke.lib.livestream.entity;

import com.ouresports.grimstroke.lib.livestream.enums.ExternLivePlatform;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author will
 * @date 2018/12/22
 */
@Data
@Accessors(chain=true)
public class LivestreamSyncRbo {
    private String id;
    private ExternLivePlatform platform;
    private String roomId;
    private String rtmp;
}
