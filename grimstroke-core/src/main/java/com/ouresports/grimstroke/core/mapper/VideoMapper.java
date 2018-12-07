package com.ouresports.grimstroke.core.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.mapper.BaseMapper;
import com.ouresports.grimstroke.core.dto.VideoDto;
import com.ouresports.grimstroke.core.entity.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/29
 */
@Component
public interface VideoMapper extends BaseMapper<Video> {
    /**
     * 获得视频列表
     * @param wrapper
     * @return
     */
    List<VideoDto> selectVideoDtos(IPage<VideoDto> page, @Param("ew") Wrapper<VideoDto> wrapper);
}
