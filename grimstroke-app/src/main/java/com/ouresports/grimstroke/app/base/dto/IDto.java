package com.ouresports.grimstroke.app.base.dto;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/27
 */
public interface IDto<T> {
    T convertTo();

    IDto<T> convertFor(T t);

    List<IDto<T>> convertFor(List<T> t);
}
