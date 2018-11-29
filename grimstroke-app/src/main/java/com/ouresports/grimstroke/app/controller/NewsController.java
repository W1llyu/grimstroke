package com.ouresports.grimstroke.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.app.base.template.ResultTemplate;
import com.ouresports.grimstroke.app.base.template.SingleTemplate;
import com.ouresports.grimstroke.app.base.template.PaginationTemplate;
import com.ouresports.grimstroke.app.vo.NewsVo;
import com.ouresports.grimstroke.core.entity.News;
import com.ouresports.grimstroke.core.entity.association.UsersInformation;
import com.ouresports.grimstroke.core.service.NewsService;
import com.ouresports.grimstroke.core.service.UsersInformationService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *
 * @author will
 * @date 2018/11/23
 */
@RestController
@RequestMapping(value="/api/news", produces="application/json;charset=UTF-8")
public class NewsController extends BaseController {
    @Resource
    private NewsService newsService;
    @Resource
    private UsersInformationService usersNewsService;

    @GetMapping(value="")
    public ResponseEntity index(@RequestParam(defaultValue="1") int currentPage,
                      @RequestParam(defaultValue="10") int per) throws Exception {
        Page<News> page = new Page<>(currentPage, per);
        QueryWrapper<News> newsQueryWrapper = new QueryWrapper<>();
        IPage<News> news = newsService.list(page, newsQueryWrapper);
        return render(new PaginationTemplate(news, NewsVo.class));
    }

    @GetMapping(value="/{id}")
    public ResponseEntity show(@PathVariable long id) throws Exception {
        News news = loadNewsById(id);
        return render(new SingleTemplate<>(news, NewsVo.class));
    }

    @PostMapping(value="/{id}/read")
    public ResponseEntity read(@PathVariable long id) throws Exception {
        authenticateUser();
        News news = loadNewsById(id);
        usersNewsService.findOrCreateBy(new UsersInformation().setNewsId(news.getId()).setUserId(currentUser.getId()));
        return render(ResultTemplate.createOk());
    }

    private News loadNewsById(long id) throws Exception {
        News news = newsService.find(id);
        if (news == null) {
            throw new NotFoundException("news");
        }
        return news;
    }
}
