package com.ouresports.grimstroke.info.rbo.admin;

import com.ouresports.grimstroke.lib.livestream.enums.ExternLivePlatform;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *
 * @author will
 * @date 2019/1/5
 */
@Data
public class ExternLiveStreamRbo {
    @NotNull(message = "直播平台不可为空")
    private ExternLivePlatform platform;
    @NotNull(message = "直播房间号不可为空")
    private String roomId;
}
