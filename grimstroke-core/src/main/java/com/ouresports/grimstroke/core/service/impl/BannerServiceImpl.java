package com.ouresports.grimstroke.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.core.dto.BannerDto;
import com.ouresports.grimstroke.core.entity.Banner;
import com.ouresports.grimstroke.core.mapper.BannerMapper;
import com.ouresports.grimstroke.core.service.BannerService;
import org.springframework.stereotype.Service;

/**
 *
 * @author will
 * @date 2018/12/5
 */
@Service
public class BannerServiceImpl extends BaseServiceImpl<BannerMapper, Banner> implements BannerService {
    @Override
    public IPage<BannerDto> getBannerDtos(IPage<BannerDto> page) {
        QueryWrapper<BannerDto> wrapper = new QueryWrapper<BannerDto>()
                .orderByDesc("`banners`.`priority`")
                .orderByDesc("`banners`.`created_at`")
                .groupBy("`banners`.`id`");
        page.setRecords(baseMapper.selectBannerDtos(page, wrapper));
        return page;
    }
}
