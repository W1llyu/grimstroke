package com.ouresports.grimstroke.im.vo.api;

import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.base.entity.User;
import com.ouresports.grimstroke.base.util.StringUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author will
 * @date 2018/12/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserVo extends BaseTo<User> {
    private Long id;
    private String name;
    private String avatar;
    private String phone;

    public String getPhone() {
        return StringUtil.mosaicPhone(phone);
    }
}
