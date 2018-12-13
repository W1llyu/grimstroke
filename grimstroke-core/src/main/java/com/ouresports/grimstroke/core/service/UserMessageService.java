package com.ouresports.grimstroke.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.service.Service;
import com.ouresports.grimstroke.core.concern.MessageTriggerable;
import com.ouresports.grimstroke.core.dto.UserMessageDto;
import com.ouresports.grimstroke.core.entity.UserMessage;
import com.ouresports.grimstroke.core.exception.ServiceException;

/**
 *
 * @author will
 * @date 2018/12/13
 */
public interface UserMessageService extends Service<UserMessage> {
    /**
     * 由触发器增加一条用户消息
     * @param trigger
     * @throws ServiceException
     */
    void addUserMessage(MessageTriggerable trigger) throws ServiceException;

    /**
     * 获得用户消息DTO
     * @param page
     * @param wrapper
     * @return
     */
    IPage<UserMessageDto> getUserMessageDto(IPage<UserMessageDto> page, QueryWrapper<UserMessage> wrapper);
}
