package com.ouresports.grimstroke.core.base.dto;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/27
 */
public abstract class BaseDto<T> implements IDto<T> {
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
    public IDto<T> convertFor(T t) {
        IDto<T> vo = null;
        try {
            vo = this.getClass().newInstance();
            BeanUtils.copyProperties(t, vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    @Override
    public List<IDto<T>> convertFor(List<T> ts) {
        List<IDto<T>> dtos = Lists.newArrayList();
        ts.forEach(t -> dtos.add(convertFor(t)));
        return dtos;
    }
}
