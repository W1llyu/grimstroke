package com.ouresports.grimstroke.core.dto;

import com.ouresports.grimstroke.core.base.dto.BaseDto;
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
public class NormalUserDto extends BaseDto<User> {
    private Long id;
    private String name;
    private String avatar;
}
