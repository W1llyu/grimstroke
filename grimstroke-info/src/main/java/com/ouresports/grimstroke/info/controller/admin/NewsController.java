package com.ouresports.grimstroke.info.controller.admin;

import com.ouresports.grimstroke.base.annotation.*;
import com.ouresports.grimstroke.info.rbo.admin.NewsRbo;
import com.ouresports.grimstroke.info.vo.admin.NewsDetailVo;
import com.ouresports.grimstroke.info.vo.admin.NewsVo;
import com.ouresports.grimstroke.info.entity.News;
import com.ouresports.grimstroke.info.service.NewsService;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
