package com.ouresports.grimstroke.base.template;

import com.alibaba.fastjson.annotation.JSONField;
import com.ouresports.grimstroke.base.entity.ITo;
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

    public SingleTemplate(List<T> list, Class<? extends ITo<T>> voClass) throws IllegalAccessException, InstantiationException {
        data = voClass.newInstance().convertFor(list);
    }

    public SingleTemplate(T t) {
        data = t;
    }

    public SingleTemplate(T t, Class<? extends ITo<T>> voClass) throws IllegalAccessException, InstantiationException {
        data = voClass.newInstance().convertFor(t);
    }
}
