package com.ouresports.grimstroke.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ouresports.grimstroke.core.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.core.concern.InfoCollectionable;
import com.ouresports.grimstroke.core.entity.InfoCollection;
import com.ouresports.grimstroke.core.entity.association.InfoCollectionsInformation;
import com.ouresports.grimstroke.core.exception.ServiceException;
import com.ouresports.grimstroke.core.mapper.InfoCollectionsInformationMapper;
import com.ouresports.grimstroke.core.service.InfoCollectionsInformationService;
import org.springframework.stereotype.Service;

import static com.ouresports.grimstroke.core.enums.ServiceError.INFORMATION_ALREADY_IN_COLLECTION;
import static com.ouresports.grimstroke.core.enums.ServiceError.INFORMATION_NOT_IN_COLLECTION;

/**
 *
 * @author will
 * @date 2018/11/29
 */
@Service
public class InfoCollectionsInformationServiceImpl extends BaseServiceImpl<InfoCollectionsInformationMapper, InfoCollectionsInformation>
    implements InfoCollectionsInformationService {
    @Override
    public void addInformation(InfoCollection infoCollection, InfoCollectionable infoCollectionable) throws ServiceException{
        QueryWrapper<InfoCollectionsInformation> wrapper = new QueryWrapper<InfoCollectionsInformation>()
                .eq("info_collection_id", infoCollection.getId())
                .eq("information_type", infoCollectionable.getInfoCollectionableType())
                .eq("information_id", infoCollectionable.getId());
        if (count(wrapper) > 0) {
            throw new ServiceException(INFORMATION_ALREADY_IN_COLLECTION);
        }
        InfoCollectionsInformation infoCollectionsInformation = new InfoCollectionsInformation()
                .setInfoCollectionId(infoCollection.getId())
                .setInformationType(infoCollectionable.getInfoCollectionableType())
                .setInformationId(infoCollectionable.getId());
        findOrCreateBy(infoCollectionsInformation);
    }

    @Override
    public void removeInformation(InfoCollection infoCollection, InfoCollectionable infoCollectionable) throws ServiceException {
        QueryWrapper<InfoCollectionsInformation> wrapper = new QueryWrapper<InfoCollectionsInformation>()
                .eq("info_collection_id", infoCollection.getId())
                .eq("information_type", infoCollectionable.getInfoCollectionableType())
                .eq("information_id", infoCollectionable.getId());
        if (count(wrapper) == 0) {
            throw new ServiceException(INFORMATION_NOT_IN_COLLECTION);
        }
        remove(wrapper);
    }
}
