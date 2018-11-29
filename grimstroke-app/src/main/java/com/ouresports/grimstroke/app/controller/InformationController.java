package com.ouresports.grimstroke.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.app.base.template.PaginationTemplate;
import com.ouresports.grimstroke.core.dto.InformationDto;
import com.ouresports.grimstroke.core.entity.InfoCollection;
import com.ouresports.grimstroke.core.entity.News;
import com.ouresports.grimstroke.core.enums.InformationSubType;
import com.ouresports.grimstroke.core.service.InfoCollectionService;
import com.ouresports.grimstroke.core.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/29
 */
@RestController
@RequestMapping(value="/api/information", produces="application/json;charset=UTF-8")
public class InformationController extends BaseController {
    @Resource
    private NewsService newsService;
    @Resource
    private InfoCollectionService infoCollectionService;

    // 每页资讯数
    private static final int NEWS_PAGE_SIZE = 5;
    // 每页专题合集数
    private static final int COL_PAGE_SIZE = 2;
    // 专题间隔
    private static final int COL_INTERVAL = 4;
    // 竞猜课堂位置
    private static final int COURSE_INDEX = 4;

    @GetMapping(value="")
    public ResponseEntity index(@RequestParam(defaultValue="1") int currentPage,
                                @RequestParam(required=false) Integer gameId) throws Exception {
        List<InformationDto> informationDtos = getOrderedInformation(currentPage, gameId);
        IPage<InformationDto> pages = new Page<>(currentPage, NEWS_PAGE_SIZE + COL_PAGE_SIZE + 2);
        pages.setRecords(informationDtos);
        pages.setTotal(getInformationCount(gameId));
        return render(new PaginationTemplate(pages));
    }

    /**
     * 获得符合排序规则的信息流
     * @param currentPage
     * @param gameId
     * @return
     */
    private List<InformationDto> getOrderedInformation(int currentPage, Integer gameId) {
        List<InformationDto> newsInfo = getNewsInformation(currentPage, gameId);
        List<InformationDto> colInfo = getInfoColInformation(currentPage, gameId);
        InformationDto courseInfo = getCourseInformation();
        InformationDto analysisInfo = getAnalysisInformation(currentPage, gameId);
        int index = 0;
        if (colInfo.size() > 0) {
            index = COL_INTERVAL - 1;
            if (index > newsInfo.size()) {
                newsInfo.add(colInfo.get(0));
            } else {
                newsInfo.add(index, colInfo.get(0));
            }
        }
        if (courseInfo != null) {
            newsInfo.add(COURSE_INDEX, courseInfo);
        }
        if (colInfo.size() > 1) {
            index = 2 * COL_INTERVAL - 1;
            if (index > newsInfo.size()) {
                newsInfo.add(colInfo.get(1));
            } else {
                newsInfo.add(index, colInfo.get(1));
            }
        }
        if (analysisInfo != null) {
            newsInfo.add(analysisInfo);
        }
        return newsInfo;
    }

    /**
     * 获得所有信息流的总数
     * @param gameId
     * @return
     */
    private int getInformationCount(Integer gameId) {
        News newsCondition = new News().setEnabled(true).setGameId(gameId);
        InfoCollection infoColCondition = new InfoCollection().setEnabled(true).setGameId(gameId);
        return newsService.count(new QueryWrapper<>(newsCondition)) + infoCollectionService.count(new QueryWrapper<>(infoColCondition));
    }

    /**
     * 获得普通资讯信息流
     * @param currentPage
     * @param gameId
     * @return
     */
    private List<InformationDto> getNewsInformation(int currentPage, Integer gameId) {
        QueryWrapper<InformationDto> wrapper = generateInformationWrapper(gameId)
                .eq("sub_type", InformationSubType.NEWS)
                .last(String.format("ORDER BY `created_at` DESC LIMIT %d, %d", (currentPage - 1) * NEWS_PAGE_SIZE, currentPage * NEWS_PAGE_SIZE));
        return newsService.getInformationDtos(wrapper);
    }

    /**
     * 获得专题合集信息流
     * @param currentPage
     * @param gameId
     * @return
     */
    private List<InformationDto> getInfoColInformation(int currentPage, Integer gameId) {
        QueryWrapper<InformationDto> wrapper = generateInformationWrapper(gameId)
            .last(String.format("ORDER BY `created_at` DESC LIMIT %d, %d", (currentPage - 1) * COL_PAGE_SIZE, currentPage * COL_PAGE_SIZE));
        return infoCollectionService.getInformationDtos(wrapper);
    }

    /**
     * 随机获得一条竞猜课堂信息流
     * @return
     */
    private InformationDto getCourseInformation() {
        QueryWrapper<InformationDto> wrapper = generateInformationWrapper(null)
                .eq("sub_type", InformationSubType.BETCOURSE)
                .last("ORDER BY RAND() LIMIT 1");
        List<InformationDto> list = newsService.getInformationDtos(wrapper);
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * 获得一条分析师信息流
     * @param currentPage
     * @param gameId
     * @return
     */
    private InformationDto getAnalysisInformation(int currentPage, Integer gameId) {
        QueryWrapper<InformationDto> wrapper = generateInformationWrapper(gameId)
                .eq("sub_type", InformationSubType.ANALYSIS)
                .last(String.format("ORDER BY `created_at` DESC LIMIT %d, %d", currentPage - 1, currentPage));
        List<InformationDto> list = newsService.getInformationDtos(wrapper);
        return list.isEmpty() ? null : list.get(0);
    }

    private QueryWrapper<InformationDto> generateInformationWrapper(Integer gameId) {
        QueryWrapper<InformationDto> wrapper = new QueryWrapper<InformationDto>().eq("enabled", true);
        if (gameId != null) {
            wrapper.eq("game_id", gameId);
        }
        return wrapper;
    }
}
