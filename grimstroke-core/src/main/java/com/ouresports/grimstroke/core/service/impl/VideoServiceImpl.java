package com.ouresports.grimstroke.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.core.dto.VideoDto;
import com.ouresports.grimstroke.core.entity.Video;
import com.ouresports.grimstroke.core.mapper.VideoMapper;
import com.ouresports.grimstroke.core.service.VideoService;
import com.ouresports.grimstroke.core.util.CollectionUtil;
import com.ouresports.grimstroke.core.util.WrapperUtil;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/29
 */
@Service
public class VideoServiceImpl extends BaseServiceImpl<VideoMapper, Video> implements VideoService {
    @Override
    public VideoDto getVideoDto(long id) throws NotFoundException {
        QueryWrapper<VideoDto> wrapper = generateVideoDtoWrapper()
                .eq("`videos`.`id`", id)
                .last("LIMIT 1");
        return CollectionUtil.getFirstElement(getVideoDtos(wrapper));
    }

    @Override
    public VideoDto getVideoDto(Video video) throws NotFoundException {
        QueryWrapper<VideoDto> wrapper = generateVideoDtoWrapper().last("LIMIT 1");
        WrapperUtil.appendEqualQuery(wrapper, video, "videos");
        return CollectionUtil.getFirstElement(getVideoDtos(wrapper));
    }

    @Override
    public List<VideoDto> getVideoDtos(Wrapper<VideoDto> wrapper) {
        return baseMapper.selectVideoDtos(null, wrapper);
    }

    @Override
    public IPage<VideoDto> getVideoDtos(IPage<VideoDto> page, Video video) {
        QueryWrapper<VideoDto> wrapper = generateVideoDtoWrapper()
                .orderByDesc("`videos`.`sticky`")
                .orderByDesc("`videos`.`created_at`");
        page.setRecords(baseMapper.selectVideoDtos(page, wrapper));
        return page;
    }

    private QueryWrapper<VideoDto> generateVideoDtoWrapper() {
        return new QueryWrapper<VideoDto>().groupBy("`videos`.`id`");
    }
}
