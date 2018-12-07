package com.ouresports.grimstroke.app.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.app.base.template.PaginationTemplate;
import com.ouresports.grimstroke.app.base.template.ResultTemplate;
import com.ouresports.grimstroke.app.base.template.SingleTemplate;
import com.ouresports.grimstroke.app.rbo.admin.NewsRbo;
import com.ouresports.grimstroke.app.vo.admin.NewsDetailVo;
import com.ouresports.grimstroke.app.vo.admin.NewsVo;
import com.ouresports.grimstroke.core.dto.NewsDto;
import com.ouresports.grimstroke.core.entity.News;
import com.ouresports.grimstroke.core.enums.InformationSubType;
import com.ouresports.grimstroke.core.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *
 * @author will
 * @date 2018/12/6
 */
@RestController(value="AdminNewsController")
@RequestMapping(value="/admin/news", produces="application/json;charset=UTF-8")
public class NewsController extends BaseController {
    @Resource
    private NewsService newsService;

    /**
     * 列表
     * @param currentPage
     * @param per
     * @param news
     * @return
     * @throws Exception
     */
    @GetMapping(value="")
    public ResponseEntity index(@RequestParam(value="page", defaultValue="1") int currentPage,
                                @RequestParam(defaultValue="10") int per,
                                News news) throws Exception {
        Page<NewsDto> page = new Page<>(currentPage, per);
        return render(new PaginationTemplate<>(newsService.getDtos(page, new QueryWrapper<>(news)), NewsVo.class));
    }

    /**
     * 详情
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{id}")
    public ResponseEntity show(@PathVariable long id) throws Exception {
        NewsDto news = newsService.getDto(id);
        return render(new SingleTemplate<>(news, NewsDetailVo.class));
    }

    /**
     * 创建资讯
     * @param rbo
     * @return
     * @throws Exception
     */
    @PostMapping(value="")
    public ResponseEntity create(@Valid @RequestBody NewsRbo rbo) throws Exception {
        newsService.save(rbo.convertTo());
        return render(ResultTemplate.createOk());
    }

    /**
     * 更新资讯
     * @param id
     * @param rbo
     * @return
     * @throws Exception
     */
    @PatchMapping(value="/{id}")
    public ResponseEntity update(@PathVariable long id,
                                 @RequestBody NewsRbo rbo) throws Exception {
        News news = rbo.convertTo();
        news.setId(id);
        newsService.updateById(news);
        return render(ResultTemplate.updateOk());
    }
}
