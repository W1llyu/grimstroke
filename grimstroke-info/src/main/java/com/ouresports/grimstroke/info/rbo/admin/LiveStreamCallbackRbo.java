package com.ouresports.grimstroke.info.rbo.admin;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *
 * @author will
 * @date 2018/12/20
 */
@Data
public class LiveStreamCallbackRbo {
    @NotNull(message="id不可为空")
    private String id;
    private String action;
    private String appname;
    private String app;
    private String node;
    private String usrargs;
    private String ip;
}
