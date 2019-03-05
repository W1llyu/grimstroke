package com.ouresports.grimstroke.im.rbo.api;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author will
 * @date 2018/12/21
 */
@Data
public class RoomMessageRbo {
    @NotNull(message="内容不可为空")
    @NotEmpty(message="内容不可为空")
    private String content;
}
