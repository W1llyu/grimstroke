package com.ouresports.grimstroke.info.vo.api;

import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.info.dto.CommentDto;
import com.ouresports.grimstroke.info.dto.LikeDto;
import com.ouresports.grimstroke.info.dto.NormalUserDto;
import com.ouresports.grimstroke.info.dto.UserMessageDto;
import com.ouresports.grimstroke.base.util.BeanUtil;
import com.ouresports.grimstroke.info.util.VoUtil;
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
        private UserVo user;
        private String content;
        private String targetType;
        private Object target;

        public void setTarget(Object target) {
            this.target = VoUtil.generateTargetVo(target);
        }

        public void setUser(NormalUserDto user) {
            if (user != null) {
                this.user = (UserVo) new UserVo().convertFor(user);
            }
        }
    }

    @Data
    static class LikeTriggerVo {
        private Long id;
        private String targetType;
        private Object target;
        private UserVo user;

        public void setTarget(Object target) {
            this.target = VoUtil.generateTargetVo(target);
        }

        public void setUser(NormalUserDto user) {
            if (user != null) {
                this.user = (UserVo) new UserVo().convertFor(user);
            }
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
}
