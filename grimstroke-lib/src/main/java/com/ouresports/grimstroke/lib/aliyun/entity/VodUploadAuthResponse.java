package com.ouresports.grimstroke.lib.aliyun.entity;

import lombok.Data;

/**
 *
 * @author will
 * @date 2018/12/5
 */
@Data
public class VodUploadAuthResponse extends AliyunResponse {
    private String videoId;
    private String uploadAddress;
    private String uploadAuth;
}
