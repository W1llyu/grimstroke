package com.ouresports.grimstroke.app.base.template;

import com.alibaba.fastjson.annotation.JSONField;
import com.ouresports.grimstroke.app.base.vo.IVo;
import lombok.Data;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/27
 */
@Data
public class SingleTemplate<T> {
    @JSONField(name="data")
    protected Object data;

    public SingleTemplate(List<T> list) {
        data = list;
    }

    public SingleTemplate(List<T> list, Class<? extends IVo> dtoClass) throws IllegalAccessException, InstantiationException {
        data = dtoClass.newInstance().convertFor(list);
    }

    public SingleTemplate(T t) {
        data = t;
    }

    public SingleTemplate(T t, Class<? extends IVo> dtoClass) throws IllegalAccessException, InstantiationException {
        data = dtoClass.newInstance().convertFor(t);
    }
}
