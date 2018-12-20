package com.ouresports.grimstroke.info.vo.api;

import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.base.entity.User;
import com.ouresports.grimstroke.base.util.BeanUtil;
import com.ouresports.grimstroke.info.dto.LiveStreamDto;
import com.ouresports.grimstroke.info.enums.LiveStreamType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author will
 * @date 2018/12/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LiveStreamVo extends BaseTo<LiveStreamDto> {
    private Long id;
    private LiveStreamType type;
    private String name;
    private UserVo user;

    public void setUser(User user) {
        if (user != null) {
            this.user = new UserVo();
            BeanUtil.copyProperties(user, this.user);
        }
    }
}
