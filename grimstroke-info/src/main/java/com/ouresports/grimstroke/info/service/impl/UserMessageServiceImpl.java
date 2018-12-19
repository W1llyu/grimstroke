package com.ouresports.grimstroke.info.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.ouresports.grimstroke.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.info.concern.MessageTriggerable;
import com.ouresports.grimstroke.info.dto.UserMessageDto;
import com.ouresports.grimstroke.info.entity.UserMessage;
import com.ouresports.grimstroke.base.exception.ServiceException;
import com.ouresports.grimstroke.info.mapper.UserMessageMapper;
import com.ouresports.grimstroke.info.service.UserMessageService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ouresports.grimstroke.base.enums.ServiceError.ALREADY_NOTIFIED;

/**
 *
 * @author will
 * @date 2018/12/13
 */
@Service
public class UserMessageServiceImpl extends BaseServiceImpl<UserMessageMapper, UserMessage> implements UserMessageService {
    @Override
    public void addUserMessage(MessageTriggerable trigger) throws ServiceException {
        if (trigger.getNotifyUser() == null) {
            return;
        }
        QueryWrapper<UserMessage> wrapper = new QueryWrapper<UserMessage>()
                .eq("user_id", trigger.getNotifyUser().getId())
                .eq("trigger_type", trigger.getTriggerableType())
                .eq("trigger_id", trigger.getId());
        if (count(wrapper) > 0) {
            throw new ServiceException(ALREADY_NOTIFIED);
        }
        UserMessage userMessage = new UserMessage()
                .setUserId(trigger.getNotifyUser().getId())
                .setTriggerType(trigger.getTriggerableType())
                .setTriggerId(trigger.getId());
        findOrCreateBy(userMessage);
    }

    @Override
    public IPage<UserMessageDto> getUserMessageDto(IPage<UserMessageDto> page, QueryWrapper<UserMessage> wrapper) {
        IPage<UserMessage> tmpPage = baseMapper.selectPage(new Page<>(page.getCurrent(), page.getSize()), wrapper);
        List<UserMessageDto> list = Lists.newArrayList();
        for (UserMessage userMessage: tmpPage.getRecords()) {
            list.add((UserMessageDto) new UserMessageDto().convertFor(userMessage));
        }
        page.setRecords(list);
        page.setTotal(tmpPage.getTotal());
        return page;
    }
}
