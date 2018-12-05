package com.ouresports.grimstroke.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.core.dto.VideoDto;
import com.ouresports.grimstroke.core.entity.Video;
import com.ouresports.grimstroke.core.mapper.VideoMapper;
import com.ouresports.grimstroke.core.service.VideoService;
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
        QueryWrapper<VideoDto> wrapper = new QueryWrapper<VideoDto>()
                .eq("`videos`.`enabled`", true)
                .eq("`videos`.`id`", id)
                .groupBy("`videos`.`id`")
                .last("LIMIT 1");
        List<VideoDto> list = getVideoDtos(wrapper);
        if (list.isEmpty()) {
            throw new NotFoundException("Video");
        }
        return list.get(0);
    }

    @Override
    public List<VideoDto> getVideoDtos(Wrapper<VideoDto> wrapper) {
        return baseMapper.selectVideoDtos(null, wrapper);
    }

    @Override
    public IPage<VideoDto> getVideoDtos(IPage<VideoDto> page, Integer gameId) {
        QueryWrapper<VideoDto> wrapper = new QueryWrapper<VideoDto>()
                .eq("`videos`.`enabled`", true)
                .orderByDesc("`videos`.`sticky`")
                .orderByDesc("`videos`.`created_at`")
                .groupBy("`videos`.`id`");
        if (gameId != null) {
            wrapper.eq("`videos`.`game_id`", gameId);
        }
        page.setRecords(baseMapper.selectVideoDtos(page, wrapper));
        return page;
    }
}
