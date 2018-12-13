package com.ouresports.grimstroke.app.rbo.api;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author will
 * @date 2018/12/14
 */
@Data
public class UserMsgBatchReadRbo {
    @NotNull(message = "id不可为空")
    private List<Long> ids;
}
