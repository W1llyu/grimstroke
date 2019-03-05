package com.ouresports.grimstroke.im.vo.api;

import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.base.entity.User;
import com.ouresports.grimstroke.im.dto.RoomMessageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author will
 * @date 2018/12/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain=true)
public class RoomMessageVo extends BaseTo<RoomMessageDto> {
    private Long id;
    private String content;
    private String createdAt;
    private UserVo user;

    public void setUser(User user) {
        if (user != null) {
            this.user = (UserVo) new UserVo().convertFor(user);
        }
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(createdAt);
    }
}
