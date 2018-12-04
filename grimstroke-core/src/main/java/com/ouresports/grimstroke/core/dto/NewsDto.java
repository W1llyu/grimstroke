package com.ouresports.grimstroke.core.dto;

import com.ouresports.grimstroke.core.enums.InformationSubType;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ouresports.grimstroke.core.enums.InformationType.NEWS;

/**
 *
 * @author will
 * @date 2018/12/3
 */
@Data
@Accessors(chain=true)
public class NewsDto {
    private Long id;
    private InformationSubType type;
    private Long associateId;
    private String title;
    private String coverImage;
    private String content;
    private Integer gameId;
    private String tagName;
    private Long browseCount;
    private Long commentCount;
    private Boolean sticky;
    private Date createdAt;

    public InformationDto toInformation() {
        InformationDto informationDto = new InformationDto();
        BeanUtils.copyProperties(this, informationDto);
        informationDto.setSubType(type).setType(NEWS);
        return informationDto;
    }

    public static List<InformationDto> toInformations(List<NewsDto> newsDtos) {
        List<InformationDto> informationDtos = new ArrayList<>();
        for (NewsDto newsDto: newsDtos) {
            informationDtos.add(newsDto.toInformation());
        }
        return informationDtos;
    }
}
