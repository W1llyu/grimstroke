package com.ouresports.grimstroke.info.dto;

import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.base.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author will
 * @date 2018/11/30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NormalUserDto extends BaseTo<User> {
    private Long id;
    private String name;
    private String avatar;
    private String phone;
    private String description;
}
