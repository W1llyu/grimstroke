package com.ouresports.grimstroke.info.dto;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ouresports.grimstroke.base.util.SpringUtil;
import com.ouresports.grimstroke.info.entity.association.NewsSeries;
import com.ouresports.grimstroke.info.entity.association.NewsTeam;
import com.ouresports.grimstroke.info.enums.InformationSubType;
import com.ouresports.grimstroke.info.service.NewsSeriesService;
import com.ouresports.grimstroke.info.service.NewsTeamService;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.ouresports.grimstroke.info.enums.InformationType.NEWS;

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
    private String author;
    private String source;
    private Integer gameId;
    private Long tagId;
    private String tagName;
    private Long browseCount;
    private Long commentCount;
    private Boolean sticky;
    private Boolean enabled;
    private Date createdAt;
    private List<Long> seriesIds;
    private List<Long> teamIds;

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

    public List<Long> getSeriesIds() {
        if (seriesIds == null) {
            NewsSeriesService newsSeriesService = SpringUtil.getBean(NewsSeriesService.class);
            List<NewsSeries> newsSeries = newsSeriesService.list(new QueryWrapper<>(new NewsSeries().setNewsId(id)));
            seriesIds = newsSeries.stream().map(NewsSeries::getSeriesId).collect(Collectors.toList());
        }
        return seriesIds;
    }

    public List<Long> getTeamIds() {
        if (teamIds == null) {
            NewsTeamService newsTeamService = SpringUtil.getBean(NewsTeamService.class);
            List<NewsTeam> newsTeams = newsTeamService.list(new QueryWrapper<>(new NewsTeam().setNewsId(id)));
            teamIds = newsTeams.stream().map(NewsTeam::getTeamId).collect(Collectors.toList());
        }
        return teamIds;
    }
}
