package com.ouresports.grimstroke.info.rbo.admin;

import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.info.entity.LiveStream;
import com.ouresports.grimstroke.lib.livestream.enums.ExternLivePlatform;
import com.ouresports.grimstroke.info.enums.LiveStreamType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 *
 * @author will
 * @date 2018/12/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LiveStreamRbo extends BaseTo<LiveStream> {
    @NotNull(message="类型不可为空")
    private LiveStreamType type;
    @NotNull(message="名字不可为空")
    private String name;
    private String description;
    private Boolean enabled;
    private Long userId;
    private ExternLivePlatform platform;
    private String roomId;
}
