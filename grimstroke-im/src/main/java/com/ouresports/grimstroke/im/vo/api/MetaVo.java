package com.ouresports.grimstroke.im.vo.api;

import com.ouresports.grimstroke.base.template.IMeta;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 *
 * @author will
 * @date 2019/1/4
 */
@Data
@Builder
public class MetaVo implements IMeta {
    private long per;
    private long totalCount;
    private long currentPage;
    private long lastId;
}
