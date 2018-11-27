package com.ouresports.grimstroke.core.service.impl;

import com.ouresports.grimstroke.core.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.core.entity.News;
import com.ouresports.grimstroke.core.service.NewsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author will
 * @date 2018/11/22
 */
@Service
@Transactional
public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService {
}
