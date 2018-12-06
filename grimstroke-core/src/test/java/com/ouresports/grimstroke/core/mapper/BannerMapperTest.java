package com.ouresports.grimstroke.core.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ouresports.grimstroke.core.GrimstrokeCoreApplicationTest;
import com.ouresports.grimstroke.core.dto.BannerDto;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by will on 2018/12/6.
 */
public class BannerMapperTest extends GrimstrokeCoreApplicationTest {
    @Resource
    private BannerMapper bannerMapper;

    @Test
    public void testSelectBannerDtos() {
        QueryWrapper<BannerDto> wrapper = new QueryWrapper<BannerDto>()
                .orderByDesc("`banners`.`priority`")
                .orderByDesc("`banners`.`created_at`");
        List<BannerDto> bannerDtoList = bannerMapper.selectBannerDtos(null, wrapper);
    }
}
