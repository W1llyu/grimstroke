package com.ouresports.grimstroke.info.controller.admin;

import com.ouresports.grimstroke.base.annotation.*;
import com.ouresports.grimstroke.base.template.ResultTemplate;
import com.ouresports.grimstroke.info.rbo.admin.NewsRbo;
import com.ouresports.grimstroke.info.rbo.admin.NewsSeriesRbo;
import com.ouresports.grimstroke.info.rbo.admin.NewsTeamRbo;
import com.ouresports.grimstroke.info.service.NewsSeriesService;
import com.ouresports.grimstroke.info.service.NewsTeamService;
import com.ouresports.grimstroke.info.vo.admin.NewsDetailVo;
import com.ouresports.grimstroke.info.vo.admin.NewsVo;
import com.ouresports.grimstroke.info.entity.News;
import com.ouresports.grimstroke.info.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *
 * @author will
 * @date 2018/12/6
 */
@RestCrudController(value="AdminNewsController")
@RequestMapping(value="/admin/news", produces="application/json;charset=UTF-8")
@RestIndex(dto=true, voClass=NewsVo.class)
@RestShow(dto=true, voClass=NewsDetailVo.class)
@RestCreate(rboClass=NewsRbo.class)
@RestUpdate(rboClass=NewsRbo.class, valid=false)
public class NewsController extends BaseController<News, NewsService> {
    @Resource
    private NewsSeriesService newsSeriesService;
    @Resource
    private NewsTeamService newsTeamService;

    /**
     * 给资讯关联比赛
     * @param id
     * @param rbo
     * @return
     * @throws Exception
     */
    @PostMapping(value="/{id}/series")
    public ResponseEntity addSeries(@PathVariable long id,
                                    @Valid @RequestBody NewsSeriesRbo rbo) throws Exception {
        newsSeriesService.addNewsSeries(baseService.find(id), rbo.getSeriesId());
        return render(ResultTemplate.createOk());
    }

    /**
     * 移除资讯和比赛的关联
     * @param id
     * @param rbo
     * @return
     * @throws Exception
     */
    @PostMapping(value="/{id}/remove_series")
    public ResponseEntity removeSeries(@PathVariable long id,
                                       @Valid @RequestBody NewsSeriesRbo rbo) throws Exception {
        newsSeriesService.removeNewsSeries(baseService.find(id), rbo.getSeriesId());
        return render(ResultTemplate.deleteOk());
    }

    /**
     * 给资讯关联队伍
     * @param id
     * @param rbo
     * @return
     * @throws Exception
     */
    @PostMapping(value="/{id}/teams")
    public ResponseEntity addTeam(@PathVariable long id,
                                  @Valid @RequestBody NewsTeamRbo rbo) throws Exception {
        newsTeamService.addNewsTeam(baseService.find(id), rbo.getTeamId());
        return render(ResultTemplate.createOk());
    }

    /**
     * 移除资讯和队伍的关联
     * @param id
     * @param rbo
     * @return
     * @throws Exception
     */
    @PostMapping(value="/{id}/remove_teams")
    public ResponseEntity removeTeam(@PathVariable long id,
                                     @Valid @RequestBody NewsTeamRbo rbo) throws Exception {
        newsTeamService.removeNewsTeam(baseService.find(id), rbo.getTeamId());
        return render(ResultTemplate.deleteOk());
    }
}
