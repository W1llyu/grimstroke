package com.ouresports.grimstroke.info.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ouresports.grimstroke.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.info.concern.InfoCollectionable;
import com.ouresports.grimstroke.info.entity.InfoCollection;
import com.ouresports.grimstroke.info.entity.association.InfoCollectionsInformation;
import com.ouresports.grimstroke.base.exception.ServiceException;
import com.ouresports.grimstroke.info.mapper.InfoCollectionsInformationMapper;
import com.ouresports.grimstroke.info.service.InfoCollectionsInformationService;
import org.springframework.stereotype.Service;

import static com.ouresports.grimstroke.base.enums.ServiceError.INFORMATION_ALREADY_IN_COLLECTION;
import static com.ouresports.grimstroke.base.enums.ServiceError.INFORMATION_NOT_IN_COLLECTION;

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
