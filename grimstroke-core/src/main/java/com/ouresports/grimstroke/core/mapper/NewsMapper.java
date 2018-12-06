package com.ouresports.grimstroke.core.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.mapper.BaseMapper;
import com.ouresports.grimstroke.core.dto.NewsDto;
import com.ouresports.grimstroke.core.entity.News;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/22
 */
@Component
public interface NewsMapper extends BaseMapper<News> {
    /**
     * 获得资讯列表
     * @param wrapper
     * @return
     */
    List<NewsDto> selectNewsDtos(IPage<NewsDto> page, @Param("ew") Wrapper<NewsDto> wrapper);
}
