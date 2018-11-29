package com.ouresports.grimstroke.core.service;

import com.ouresports.grimstroke.core.base.service.Service;
import com.ouresports.grimstroke.core.concern.InfoCollectionable;
import com.ouresports.grimstroke.core.entity.InfoCollection;
import com.ouresports.grimstroke.core.entity.association.InfoCollectionsInformation;

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
    void addInformation(InfoCollection infoCollection, InfoCollectionable infoCollectionable);
}