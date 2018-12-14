package com.ouresports.grimstroke.app.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.app.base.annotation.AuthToken;
import com.ouresports.grimstroke.app.base.template.PaginationTemplate;
import com.ouresports.grimstroke.app.base.template.ResultTemplate;
import com.ouresports.grimstroke.app.rbo.api.UserMsgBatchReadRbo;
import com.ouresports.grimstroke.app.vo.api.UserMessageVo;
import com.ouresports.grimstroke.core.dto.UserMessageDto;
import com.ouresports.grimstroke.core.entity.UserMessage;
import com.ouresports.grimstroke.core.service.UserMessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *
 * @author will
 * @date 2018/12/13
 */
@RestController
@RequestMapping(value="/api/user_messages", produces="application/json;charset=UTF-8")
public class UserMessageController extends BaseController {
    @Resource
    private UserMessageService userMessageService;

    @AuthToken
    @GetMapping(value="")
    public ResponseEntity index(@RequestParam(value="page", defaultValue="1") int currentPage,
                                @RequestParam(defaultValue="10") int per,
                                @RequestParam(value="is_read") Boolean isRead,
                                @RequestParam(value="last_time", required=false) String lastTime) throws Exception {
        Page<UserMessageDto> page = new Page<>(currentPage, per);
        UserMessage userMessage = new UserMessage().setUserId(getCurrentUser().getId()).setIsRead(isRead);
        QueryWrapper<UserMessage> wrapper = new QueryWrapper<>(userMessage)
                .orderByDesc("`created_at`");
        if (lastTime != null) {
            wrapper.lt("`created_at`", lastTime);
        }
        IPage<UserMessageDto> messageDtos = userMessageService.getUserMessageDto(page, wrapper);
        return render(new PaginationTemplate<>(messageDtos, UserMessageVo.class));
    }

    @AuthToken
    @PostMapping(value="/batch_read")
    public ResponseEntity batchRead(@RequestBody UserMsgBatchReadRbo rbo) throws Exception {
        QueryWrapper<UserMessage> wrapper = new QueryWrapper<UserMessage>()
                .eq("user_id", getCurrentUser().getId())
                .in("id", rbo.getIds());
        userMessageService.update(new UserMessage().setIsRead(true), wrapper);
        return render(ResultTemplate.updateOk());
    }
}
