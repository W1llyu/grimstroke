package com.ouresports.grimstroke.im.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ouresports.grimstroke.base.mapper.BaseMapper;
import com.ouresports.grimstroke.im.entity.MatchSeries;
import org.springframework.stereotype.Component;

/**
 *
 * @author will
 * @date 2018/12/18
 */
@Component
@DS("arcwarden")
public interface MatchSeriesMapper extends BaseMapper<MatchSeries> {
}
