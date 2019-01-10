package com.ouresports.grimstroke.info.vo.api;

import com.ouresports.grimstroke.base.entity.BaseTo;
import com.ouresports.grimstroke.base.util.StringUtil;
import com.ouresports.grimstroke.info.dto.NormalUserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author will
 * @date 2018/12/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AnalystVo extends BaseTo<NormalUserDto> {
    private Long id;
    private String name;
    private String avatar;
    private String phone;
    private String description;

    public String getPhone() {
        return StringUtil.mosaicPhone(phone);
    }
}
