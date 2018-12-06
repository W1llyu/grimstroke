package com.ouresports.grimstroke.core.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.service.Service;
import com.ouresports.grimstroke.core.dto.InfoCollectionDto;
import com.ouresports.grimstroke.core.dto.InformationDto;
import com.ouresports.grimstroke.core.dto.NewsDto;
import com.ouresports.grimstroke.core.entity.InfoCollection;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/29
 */
public interface InfoCollectionService extends Service<InfoCollection> {
    /**
     * 获得一条专栏详情
     * @param id
     * @return
     */
    InfoCollectionDto getInfoCollectionDto(long id) throws NotFoundException;

    /**
     * 获得一条专栏详情
     * @param infoCollection
     * @return
     */
    InfoCollectionDto getInfoCollectionDto(InfoCollection infoCollection) throws NotFoundException;

    /**
     * 获得专栏列表
     * @param wrapper
     * @return
     */
    List<InfoCollectionDto> getInfoCollectionDtos(Wrapper<InfoCollectionDto> wrapper);

    /**
     * 获得专栏DTO Page
     * @param page
     * @param infoCollection
     * @return
     */
    IPage<InfoCollectionDto> getInfoCollectionDtos(IPage<InfoCollectionDto> page, InfoCollection infoCollection);

    /**
     * 获取专栏下所有的资讯
     * @param page
     * @param id
     * @return
     */
    IPage<InformationDto> getInformationDtoOfCol(IPage<InformationDto> page, long id);
}
