package com.ouresports.grimstroke.info.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.base.entity.BaseEntity;
import com.ouresports.grimstroke.info.enums.LiveStreamType;
import com.ouresports.grimstroke.lib.livestream.enums.ExternLivePlatform;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author will
 * @date 2018/12/19
 */
@TableName("live_streams")
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain=true)
public class LiveStream extends BaseEntity {
    private LiveStreamType type;
    private String name;
    private String description;
    private Boolean active;
    private Boolean enabled;
    private ExternLivePlatform platform;
    private Long userId;
}
