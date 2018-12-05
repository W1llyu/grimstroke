package com.ouresports.grimstroke.lib.aliyun.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 *
 * @author will
 * @date 2018/12/5
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VodDetailResponse extends AliyunResponse {
    private VideoDetail video;

    @Data
    public static class VideoDetail {
        private String videoId;
        private String title;
        private String description;
        private Float duration;
        private String coverURL;
        private String status;
        private String creationTime;
        private Long size;
        private String cateId;
        private String cateName;
        private String tags;
        private Snapshot snapshots;
    }

    @Data
    public static class Snapshot {
        private Set<String> snapshot;
    }
}
