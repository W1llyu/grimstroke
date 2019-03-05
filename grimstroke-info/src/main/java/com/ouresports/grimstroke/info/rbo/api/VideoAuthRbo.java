package com.ouresports.grimstroke.info.rbo.api;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *
 * @author will
 * @date 2018/12/5
 */
@Data
public class VideoAuthRbo {
    @NotNull(message="videoId不可为空")
    private String videoId;
}
