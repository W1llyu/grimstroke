package com.ouresports.grimstroke.info.mapper;

import com.ouresports.grimstroke.base.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.info.dto.InformationDto;
import com.ouresports.grimstroke.info.entity.InfoCollection;
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
