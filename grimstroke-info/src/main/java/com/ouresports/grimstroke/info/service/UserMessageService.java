package com.ouresports.grimstroke.info.service;

import com.ouresports.grimstroke.base.service.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.info.concern.MessageTriggerable;
import com.ouresports.grimstroke.info.dto.UserMessageDto;
import com.ouresports.grimstroke.info.entity.UserMessage;
import com.ouresports.grimstroke.base.exception.ServiceException;

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
