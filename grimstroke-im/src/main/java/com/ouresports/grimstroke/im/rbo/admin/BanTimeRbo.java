package com.ouresports.grimstroke.im.rbo.admin;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 *
 * @author will
 * @date 2018/12/22
 */
@Data
public class BanTimeRbo {
    @NotNull(message="时间不可为空")
    private Date banTime;
}
