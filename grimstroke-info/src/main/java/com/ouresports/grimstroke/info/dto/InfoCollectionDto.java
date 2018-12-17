package com.ouresports.grimstroke.info.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ouresports.grimstroke.info.enums.InformationType.COLLECTION;

/**
 *
 * @author will
 * @date 2018/12/3
 */
@Data
public class InfoCollectionDto {
    private Long id;
    private String title;
    private String subTitle;
    private String coverImage;
    private Integer gameId;
    private Boolean sticky;
    private Boolean enabled;
    private Long newsCount;
    private Long browseCount;
    private Long commentCount;
    private Date createdAt;
    private Date lastInfoTime;

    public InformationDto toInformation() {
        InformationDto informationDto = new InformationDto();
        BeanUtils.copyProperties(this, informationDto);
        informationDto.setType(COLLECTION);
        informationDto.setCreatedAt(lastInfoTime);
        return informationDto;
    }

    public static List<InformationDto> toInformations(List<InfoCollectionDto> infoCollectionDtos) {
        List<InformationDto> informationDtos = new ArrayList<>();
        for (InfoCollectionDto infoCollectionDto: infoCollectionDtos) {
            informationDtos.add(infoCollectionDto.toInformation());
        }
        return informationDtos;
    }
}
