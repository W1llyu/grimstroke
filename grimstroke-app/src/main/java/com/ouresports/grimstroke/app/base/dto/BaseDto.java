package com.ouresports.grimstroke.app.base.dto;

import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/27
 */
public abstract class BaseDto<T> implements IDto<T> {
    @Override
    public T convertTo() {
        throw new AssertionError("not avaliable");
    }

    @Override
    public IDto<T> convertFor(T t) {
        throw new AssertionError("not avaliable");
    }

    @Override
    public List<IDto<T>> convertFor(List<T> ts) {
        List<IDto<T>> dtos = Lists.newArrayList();
        ts.forEach(t -> dtos.add(convertFor(t)));
        return dtos;
    }
}
