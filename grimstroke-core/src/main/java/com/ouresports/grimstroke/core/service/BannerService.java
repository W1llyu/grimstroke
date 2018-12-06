package com.ouresports.grimstroke.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.service.Service;
import com.ouresports.grimstroke.core.dto.BannerDto;
import com.ouresports.grimstroke.core.entity.Banner;
import org.apache.ibatis.javassist.NotFoundException;

/**
 *
 * @author will
 * @date 2018/12/5
 */
public interface BannerService extends Service<Banner> {
    /**
     * 获得一个banner详情
     * @param id
     * @return
     * @throws NotFoundException
     */
    BannerDto getBannerDto(long id) throws NotFoundException;

    BannerDto getBannerDto(Banner banner) throws NotFoundException;

    /**
     * 获得banner列表分页
     * @param page
     * @return
     */
    IPage<BannerDto> getBannerDtos(IPage<BannerDto> page, Banner banner);
}
