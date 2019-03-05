package com.ouresports.grimstroke.lib.aliyun.entity;

import lombok.Data;

/**
 *
 * @author will
 * @date 2018/12/5
 */
@Data
public class VodUploadInfo {
    private String title;
    private String fileName;
    private String fileSize;
    private String description;
    private String coverUrl;
    private Long cateId;
    private String tags;
    private String templateGroupId;
    private String storageLocation;
}
