package com.ouresports.grimstroke.app.base.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GeneralDto<T> extends BaseDto<T> {
    @JSONField(name="data")
    protected Object data;

    public GeneralDto(List<T> list) {
        data = list;
    }

    public GeneralDto(List<T> list, Class<? extends IDto> dtoClass) throws IllegalAccessException, InstantiationException {
        data = dtoClass.newInstance().convertFor(list);
    }

    public GeneralDto(T t) {
        data = t;
    }

    public GeneralDto(T t, Class<? extends IDto> dtoClass) throws IllegalAccessException, InstantiationException {
        data = dtoClass.newInstance().convertFor(t);
    }
}
