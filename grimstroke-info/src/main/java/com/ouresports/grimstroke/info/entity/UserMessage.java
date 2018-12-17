package com.ouresports.grimstroke.info.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author will
 * @date 2018/12/13
 */
@TableName("user_messages")
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain=true)
public class UserMessage extends BaseEntity {
    private Long userId;
    private String triggerType;
    private Long triggerId;
    private String message;
    private Boolean isRead;
}
