package com.ouresports.grimstroke.info.service;

import com.ouresports.grimstroke.base.service.Service;
import com.ouresports.grimstroke.info.concern.InfoCollectionable;
import com.ouresports.grimstroke.info.entity.InfoCollection;
import com.ouresports.grimstroke.info.entity.association.InfoCollectionsInformation;
import com.ouresports.grimstroke.base.exception.ServiceException;

/**
 *
 * @author will
 * @date 2018/11/29
 */
public interface InfoCollectionsInformationService extends Service<InfoCollectionsInformation> {
    /**
     * 添加一条信息流到专题合集中
     * @param infoCollection
     * @param infoCollectionable
     */
    void addInformation(InfoCollection infoCollection, InfoCollectionable infoCollectionable) throws ServiceException;

    /**
     * 从专题合集中删除一条信息流
     * @param infoCollection
     * @param infoCollectionable
     * @throws ServiceException
     */
    void removeInformation(InfoCollection infoCollection, InfoCollectionable infoCollectionable) throws ServiceException;
}
