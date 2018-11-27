package com.ouresports.grimstroke.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.app.base.controller.BaseController;
import com.ouresports.grimstroke.app.base.dto.GeneralDto;
import com.ouresports.grimstroke.app.base.dto.PaginationDto;
import com.ouresports.grimstroke.app.dto.NewsDto;
import com.ouresports.grimstroke.core.entity.News;
import com.ouresports.grimstroke.core.service.NewsService;
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

    @GetMapping(value="")
    public ResponseEntity index(@RequestParam(defaultValue="1") int currentPage,
                      @RequestParam(defaultValue="10") int per) throws Exception {
        Page<News> page = new Page<>(currentPage, per);
        QueryWrapper<News> newsQueryWrapper = new QueryWrapper<>();
        IPage<News> news = newsService.where(page, newsQueryWrapper);
        return render(new PaginationDto<>(news, NewsDto.class));
    }

    @GetMapping(value="/{id}")
    public ResponseEntity show(@PathVariable long id) throws Exception {
        News news = newsService.find(id);
        if (news == null) {
            throw new NotFoundException("news");
        }
        return render(new GeneralDto<>(news, NewsDto.class));
    }
}
