package com.ouresports.grimstroke.info.service;

import com.ouresports.grimstroke.base.service.Service;
import com.ouresports.grimstroke.info.entity.LiveStream;
import com.ouresports.grimstroke.info.enums.StreamTemplate;
import com.ouresports.grimstroke.lib.livestream.enums.ExternLivePlatform;

/**
 *
 * @author will
 * @date 2018/12/19
 */
public interface LiveStreamService extends Service<LiveStream> {
    String getPushUrl(LiveStream liveStream);

    String getPlayUrl(LiveStream liveStream, StreamTemplate template);
}
