package com.ouresports.grimstroke.core.service.impl;

import com.ouresports.grimstroke.core.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.core.concern.InfoCollectionable;
import com.ouresports.grimstroke.core.entity.InfoCollection;
import com.ouresports.grimstroke.core.entity.association.InfoCollectionsInformation;
import com.ouresports.grimstroke.core.mapper.InfoCollectionsInformationMapper;
import com.ouresports.grimstroke.core.service.InfoCollectionsInformationService;
import org.springframework.stereotype.Service;

/**
 *
 * @author will
 * @date 2018/11/29
 */
@Service
public class InfoCollectionsInformationServiceImpl extends BaseServiceImpl<InfoCollectionsInformationMapper, InfoCollectionsInformation>
    implements InfoCollectionsInformationService {
    @Override
    public void addInformation(InfoCollection infoCollection, InfoCollectionable infoCollectionable) {
        InfoCollectionsInformation infoCollectionsInformation = new InfoCollectionsInformation()
                .setInfoCollectionId(infoCollection.getId())
                .setInformationType(infoCollectionable.getInfoCollectionableType())
                .setInformationId(infoCollectionable.getId());
        findOrCreateBy(infoCollectionsInformation);
    }
}
