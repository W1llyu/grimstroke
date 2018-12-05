package com.ouresports.grimstroke.core.base.entity;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/27
 */
public abstract class BaseTo<T> implements ITo<T> {
    @Override
    public T convertTo() {
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        T t = null;
        try {
            t = entityClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(this, t);
        return t;
    }

    @Override
    public ITo<T> convertFor(T t) {
        ITo<T> bto = null;
        try {
            bto = this.getClass().newInstance();
            BeanUtils.copyProperties(t, bto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bto;
    }

    @Override
    public List<ITo<T>> convertFor(List<T> ts) {
        List<ITo<T>> dtos = Lists.newArrayList();
        ts.forEach(t -> dtos.add(convertFor(t)));
        return dtos;
    }
}
