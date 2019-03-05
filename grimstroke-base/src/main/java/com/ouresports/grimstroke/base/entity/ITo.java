package com.ouresports.grimstroke.base.entity;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/12/5
 */
public interface ITo<T> {
    T convertTo();

    ITo<T> convertFor(T t);

    List<ITo<T>> convertFor(List<T> t);
}
