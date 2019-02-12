package com.ouresports.grimstroke.info.service.impl;

import com.ouresports.grimstroke.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.info.entity.LiveStream;
import com.ouresports.grimstroke.info.enums.StreamTemplate;
import com.ouresports.grimstroke.info.mapper.LiveStreamMapper;
import com.ouresports.grimstroke.info.service.LiveStreamService;
import com.ouresports.grimstroke.lib.aliyun.service.AliyunLiveStreamService;
import com.ouresports.grimstroke.lib.livestream.enums.ExternLivePlatform;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ouresports.grimstroke.lib.livestream.enums.ExternLivePlatform.Twitch;

/**
 *
 * @author will
 * @date 2018/12/19
 */
@Service
public class LiveStreamServiceImpl extends BaseServiceImpl<LiveStreamMapper, LiveStream> implements LiveStreamService {
    @Resource
    private AliyunLiveStreamService aliyunLiveStreamService;

    @Override
    public String getPushUrl(LiveStream liveStream) {
        return aliyunLiveStreamService.generateStreamPushUrl(liveStream.getId().toString());
    }

    @Override
    public String getPlayUrl(LiveStream liveStream, StreamTemplate template) {
        if (liveStream.getPlatform() == Twitch) {
            return aliyunLiveStreamService.generateFlvStreamPlayUrl(liveStream.getId().toString(), null);
        } else {
            return aliyunLiveStreamService.generateFlvStreamPlayUrl(liveStream.getId().toString(), template.toString());
        }
    }
}
