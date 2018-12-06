package com.ouresports.grimstroke.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.core.dto.BannerDto;
import com.ouresports.grimstroke.core.entity.Banner;
import com.ouresports.grimstroke.core.mapper.BannerMapper;
import com.ouresports.grimstroke.core.service.BannerService;
import com.ouresports.grimstroke.core.util.CollectionUtil;
import com.ouresports.grimstroke.core.util.WrapperUtil;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author will
 * @date 2018/12/5
 */
@Service
public class BannerServiceImpl extends BaseServiceImpl<BannerMapper, Banner> implements BannerService {
    @Override
    public BannerDto getBannerDto(long id) throws NotFoundException {
        QueryWrapper<BannerDto> wrapper = generateGeneralQuery()
                .eq("`banners`.`id`", id)
                .last("LIMIT 1");
        return CollectionUtil.getFirstElement(baseMapper.selectBannerDtos(null, wrapper));
    }

    @Override
    public BannerDto getBannerDto(Banner banner) throws NotFoundException {
        QueryWrapper<BannerDto> wrapper = generateGeneralQuery();
        WrapperUtil.appendEqualQuery(wrapper, banner, "banners");
        return CollectionUtil.getFirstElement(baseMapper.selectBannerDtos(null, wrapper));
    }

    @Override
    public IPage<BannerDto> getBannerDtos(IPage<BannerDto> page, Banner banner) {
        QueryWrapper<BannerDto> wrapper = generateGeneralQuery()
                .orderByDesc("`banners`.`priority`")
                .orderByDesc("`banners`.`created_at`");
        WrapperUtil.appendEqualQuery(wrapper, banner, "banners");
        page.setRecords(baseMapper.selectBannerDtos(page, wrapper));
        return page;
    }

    private QueryWrapper<BannerDto> generateGeneralQuery() {
        return new QueryWrapper<BannerDto>().groupBy("`banners`.`id`");
    }
}
