package com.ouresports.grimstroke.core.dto;

import com.ouresports.grimstroke.core.base.entity.BaseTo;
import com.ouresports.grimstroke.core.entity.User;
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
}
