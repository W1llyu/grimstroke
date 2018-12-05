package com.ouresports.grimstroke.lib.aliyun.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 *
 * @author will
 * @date 2018/12/5
 */
@Data
public class AliyunResponse {
    @JSONField(serialize = false)
    private String requestId;
    @JSONField(serialize = false)
    private String code;
    @JSONField(serialize = false)
    private String message;
}
