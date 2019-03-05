package com.ouresports.grimstroke.im.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.ouresports.grimstroke.base.mapper.BaseMapper;
import com.ouresports.grimstroke.im.entity.RoomMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 *
 * @author will
 * @date 2018/12/21
 */
@Component
public interface RoomMessageMapper extends BaseMapper<RoomMessage> {
    long selectUserCount(@Param("ew") Wrapper<RoomMessage> wrapper);
}
