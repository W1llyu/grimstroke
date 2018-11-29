package com.ouresports.grimstroke.core.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.dto.InfoCollectionDto;
import com.ouresports.grimstroke.core.entity.InfoCollection;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/29
 */
@Component
public interface InfoCollectionMapper extends BaseMapper<InfoCollection> {
    List<InfoCollectionDto> selectInfoCollectionDtos(IPage<InfoCollectionDto> page, @Param("ew") Wrapper<InfoCollection> wrapper);
}
