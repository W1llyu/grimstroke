package com.ouresports.grimstroke.core.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.service.Service;
import com.ouresports.grimstroke.core.dto.VideoDto;
import com.ouresports.grimstroke.core.entity.Video;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/29
 */
public interface VideoService extends Service<Video> {
    /**
     * 获得一条视频
     * @param id
     * @return
     */
    VideoDto getVideoDto(long id) throws NotFoundException;

    /**
     * 获得视频列表
     * @param wrapper
     * @return
     */
    List<VideoDto> getVideoDtos(Wrapper<VideoDto> wrapper);

    /**
     * 获得视频列表分页
     * @param page
     * @param gameId
     * @return
     */
    IPage<VideoDto> getVideoDtos(IPage<VideoDto> page, Integer gameId);
}
