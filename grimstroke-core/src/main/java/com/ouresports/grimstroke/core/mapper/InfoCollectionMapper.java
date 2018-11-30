package com.ouresports.grimstroke.core.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.dto.InformationDto;
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
    /**
     * 获得专栏信息流
     * @param wrapper
     * @return
     */
    List<InformationDto> selectInformationDtos(IPage<InformationDto> page, @Param("ew") Wrapper<InformationDto> wrapper);

    /**
     * 获得专栏下的所有信息流
     * @param page
     * @param infoCollectionId
     * @param wrapper
     * @return
     */
    List<InformationDto> selectInformationDtosOfCol(IPage<InformationDto> page,
                                               @Param("id") long infoCollectionId,
                                               @Param("ew") Wrapper<InformationDto> wrapper);
}
