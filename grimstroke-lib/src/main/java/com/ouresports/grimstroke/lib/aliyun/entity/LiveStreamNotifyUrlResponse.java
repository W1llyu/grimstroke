package com.ouresports.grimstroke.lib.aliyun.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author will
 * @date 2018/12/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LiveStreamNotifyUrlResponse extends AliyunResponse {
    private NotifyUrlConfig liveStreamsNotifyConfig;

    @Data
    static class NotifyUrlConfig {
        private String domainName;
        private String notifyUrl;
        private String authKey;
        private String authType;
    }
}
