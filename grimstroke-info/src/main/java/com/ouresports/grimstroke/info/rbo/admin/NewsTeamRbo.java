package com.ouresports.grimstroke.info.rbo.admin;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *
 * @author will
 * @date 2018/12/17
 */
@Data
public class NewsTeamRbo {
    @NotNull(message="队伍id不可为空")
    private Long teamId;
}
