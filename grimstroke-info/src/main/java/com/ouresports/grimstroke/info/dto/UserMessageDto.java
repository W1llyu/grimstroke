package com.ouresports.grimstroke.info.dto;

import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.info.concern.Targetable;
import com.ouresports.grimstroke.info.entity.UserMessage;
import com.ouresports.grimstroke.info.service.GeneralService;
import com.ouresports.grimstroke.base.util.SpringUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/12/13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserMessageDto extends BaseTo<UserMessage> {
    private Long id;
    private String triggerType;
    private Long triggerId;
    private String message;
    private Date createdAt;
    private Object trigger;
    private Boolean isRead;

    public Object getTrigger() {
        if (trigger == null) {
            initTrigger();
        }
        return trigger;
    }

    private void initTrigger() {
        GeneralService generalService = SpringUtil.getBean(GeneralService.class);
        trigger = generalService.getPolymorphicDto(triggerType, triggerId);
        if (trigger instanceof Targetable) {
            ((Targetable) trigger).setTarget(generalService.getPolymorphicEntity(((Targetable) trigger).getTargetType(), ((Targetable) trigger).getTargetId()));
        }
    }
}
