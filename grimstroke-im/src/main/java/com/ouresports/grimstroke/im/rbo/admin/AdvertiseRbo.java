package com.ouresports.grimstroke.im.rbo.admin;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *
 * @author will
 * @date 2019/1/8
 */
@Data
public class AdvertiseRbo {
    @NotNull(message="url不可为空")
    private String url;
}
