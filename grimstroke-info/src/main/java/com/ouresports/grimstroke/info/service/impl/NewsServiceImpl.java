package com.ouresports.grimstroke.info.service.impl;

import com.ouresports.grimstroke.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.info.entity.News;
import com.ouresports.grimstroke.info.mapper.NewsMapper;
import com.ouresports.grimstroke.info.service.NewsService;
import org.springframework.stereotype.Service;

/**
 *
 * @author will
 * @date 2018/11/22
 */
@Service
public class NewsServiceImpl extends BaseServiceImpl<NewsMapper, News> implements NewsService {
}
