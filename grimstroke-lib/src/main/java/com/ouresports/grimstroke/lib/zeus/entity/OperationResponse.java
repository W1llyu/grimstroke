package com.ouresports.grimstroke.lib.zeus.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 *
 * @author will
 * @date 2019/1/10
 */
@Data
public class OperationResponse {
    @JSONField(name="error_code")
    private Integer errorCode;
    @JSONField(name="error_message")
    private String errorMessage;
}
