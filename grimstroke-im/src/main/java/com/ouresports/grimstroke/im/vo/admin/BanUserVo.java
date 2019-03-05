package com.ouresports.grimstroke.im.vo.admin;

import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.base.entity.User;
import com.ouresports.grimstroke.im.dto.ChatRoomBanDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/12/22
 */
@Getter
public class BanUserVo extends BaseTo<ChatRoomBanDto> {
    private Long id;
    private String name;
    private String avatar;
    private String phone;
    @Setter
    private Date banTime;

    public void setUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.avatar = user.getAvatar();
        this.phone = user.getPhone();
    }
}
