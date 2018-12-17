package com.ouresports.grimstroke.info.vo.api;

import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.info.dto.CommentDto;
import com.ouresports.grimstroke.info.dto.LikeDto;
import com.ouresports.grimstroke.info.dto.NormalUserDto;
import com.ouresports.grimstroke.info.dto.UserMessageDto;
import com.ouresports.grimstroke.info.entity.Comment;
import com.ouresports.grimstroke.info.entity.News;
import com.ouresports.grimstroke.base.util.BeanUtil;
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
public class UserMessageVo extends BaseTo<UserMessageDto> {
    private Long id;
    private String message;
    private String triggerType;
    private Object trigger;
    private Date createdAt;
    private Boolean isRead;

    @Data
    static class CommentTriggerVo {
        private Long id;
        private NormalUserDto user;
        private String content;
        private String targetType;
        private Object target;

        public void setTarget(Object target) {
            this.target = generateTargetVo(target);
        }
    }

    @Data
    static class LikeTriggerVo {
        private Long id;
        private String targetType;
        private Object target;

        public void setTarget(Object target) {
            this.target = generateTargetVo(target);
        }
    }

    @Data
    static class NewsTargetVo {
        private Long id;
        private String title;
    }

    @Data
    static class CommentTargetVo {
        private Long id;
        private String content;
    }

    public void setTrigger(Object trigger) {
        this.trigger = generateTriggerVo(trigger);
    }

    private static Object generateTriggerVo(Object trigger) {
        Object triggerVo;
        if (trigger == null) {
            return null;
        }
        if (trigger.getClass() == CommentDto.class) {
            triggerVo = new CommentTriggerVo();
        } else if (trigger.getClass() == LikeDto.class) {
            triggerVo = new LikeTriggerVo();
        } else {
            return trigger;
        }
        BeanUtil.copyProperties(trigger, triggerVo);
        return triggerVo;
    }

    private static Object generateTargetVo(Object target) {
        Object targetVo;
        if (target == null) {
            return null;
        }
        if (target.getClass() == Comment.class) {
            targetVo = new CommentTargetVo();
        } else if (target.getClass() == News.class) {
            targetVo = new NewsTargetVo();
        } else {
            return target;
        }
        BeanUtil.copyProperties(target, targetVo);
        return targetVo;
    }
}
