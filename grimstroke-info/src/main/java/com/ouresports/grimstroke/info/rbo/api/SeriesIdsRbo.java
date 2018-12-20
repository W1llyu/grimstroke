package com.ouresports.grimstroke.info.rbo.api;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author will
 * @date 2018/12/20
 */
@Data
public class SeriesIdsRbo {
    @NotNull(message = "id不可为空")
    private List<Long> ids;
}
