package com.ouresports.grimstroke.info.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.info.dto.InfoCollectionDto;
import com.ouresports.grimstroke.info.dto.NewsDto;
import com.ouresports.grimstroke.info.service.InformationService;
import com.ouresports.grimstroke.info.dto.InformationDto;
import com.ouresports.grimstroke.info.entity.InfoCollection;
import com.ouresports.grimstroke.info.entity.News;
import com.ouresports.grimstroke.info.enums.InformationSubType;
import com.ouresports.grimstroke.info.service.InfoCollectionService;
import com.ouresports.grimstroke.info.service.NewsService;
import com.ouresports.grimstroke.base.util.CollectionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.ouresports.grimstroke.info.enums.InformationSubType.BetCourse;

/**
 *
 * @author will
 * @date 2018/12/3
 */
@Service
public class InformationServiceImpl implements InformationService{
    @Resource
    private NewsService newsService;
    @Resource
    private InfoCollectionService infoCollectionService;

    // 每页资讯数
    private static final int NEWS_PAGE_SIZE = 3;
    // 每页专题合集数
    private static final int COL_PAGE_SIZE = 2;
    // 专题间隔
    private static final int COL_INTERVAL = 4;
    // 竞猜课堂位置
    private static final int COURSE_INDEX = 4;

    /**
     * 获得符合排序规则的信息流
     * 每页按照 3资讯 1专栏 1课堂 2资讯 1专栏 1分析 排列
     * @param page
     * @param gameId
     * @return
     */
    @Override
    public IPage<InformationDto> getOrderedInformation(int page, Integer gameId) {
        List<InformationDto> newsInfo = getNewsInformation(page, gameId);
        InformationDto courseInfo = getCourseInformation();
        if (courseInfo != null) {
            CollectionUtil.addElement(newsInfo, courseInfo, COURSE_INDEX);
        }
        IPage<InformationDto> pages = new Page<>(page, NEWS_PAGE_SIZE + 1);
        pages.setRecords(newsInfo);
        pages.setTotal(getInformationCount(gameId));
        return pages;
    }

    /**
     * 获得所有信息流的总数
     * @param gameId
     * @return
     */
    private int getInformationCount(Integer gameId) {
        News newsCondition = new News().setEnabled(true).setGameId(gameId);
        InfoCollection infoColCondition = new InfoCollection().setEnabled(true).setGameId(gameId);
        return newsService.count(new QueryWrapper<>(newsCondition).ne("`news`.`type`", BetCourse)) + infoCollectionService.count(new QueryWrapper<>(infoColCondition));
    }

    /**
     * 获得普通资讯信息流
     * @param currentPage
     * @param gameId
     * @return
     */
    private List<InformationDto> getNewsInformation(int currentPage, Integer gameId) {
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        initInformationWrapper(wrapper, gameId);
        wrapper.eq("`news`.`type`", InformationSubType.News)
                .orderByDesc("`news`.`sticky`")
                .orderByDesc("`news`.`created_at`")
                .last(String.format("LIMIT %d, %d", (currentPage - 1) * NEWS_PAGE_SIZE, NEWS_PAGE_SIZE));
        return NewsDto.toInformations(newsService.getDtos(wrapper));
    }

    /**
     * 获得专题合集信息流
     * @param currentPage
     * @param gameId
     * @return
     */
    private List<InformationDto> getInfoColInformation(int currentPage, Integer gameId) {
        QueryWrapper<InfoCollection> wrapper = new QueryWrapper<>();
        wrapper.eq("`info_collections`.`enabled`", true);
        if (gameId != null) {
            wrapper.eq("`info_collections`.`game_id`", gameId);
        }
        wrapper.orderByDesc("`info_collections`.`sticky`")
                .orderByDesc("`last_info_time`")
                .last(String.format("LIMIT %d, %d", (currentPage - 1) * COL_PAGE_SIZE, COL_PAGE_SIZE));
        return InfoCollectionDto.toInformations(infoCollectionService.getDtos(wrapper));
    }

    /**
     * 随机获得一条竞猜课堂信息流
     * @return
     */
    private InformationDto getCourseInformation() {
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        initInformationWrapper(wrapper, null);
        wrapper.eq("`news`.`type`", BetCourse)
                .last("ORDER BY RAND() LIMIT 1");
        List<NewsDto> list = newsService.getDtos(wrapper);
        InformationDto dto = null;
        if (!list.isEmpty()) {
            dto = list.get(0).toInformation();
        }
        return dto;
    }

    /**
     * 获得一条分析师信息流
     * @param currentPage
     * @param gameId
     * @return
     */
    private InformationDto getAnalysisInformation(int currentPage, Integer gameId) {
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        initInformationWrapper(wrapper, gameId);
        wrapper.eq("`news`.`type`", InformationSubType.Analysis)
                .orderByDesc("`news`.`sticky`")
                .orderByDesc("`news`.`created_at`")
                .last(String.format("LIMIT %d, %d", currentPage - 1, 1));
        List<NewsDto> list = newsService.getDtos(wrapper);
        InformationDto dto = null;
        if (!list.isEmpty()) {
            dto = list.get(0).toInformation();
        }
        return dto;
    }

    private <T> void initInformationWrapper(QueryWrapper<T> wrapper, Integer gameId) {
        wrapper.eq("`news`.`enabled`", true);
        if (gameId != null) {
            wrapper.eq("`news`.`game_id`", gameId);
        }
    }
}
