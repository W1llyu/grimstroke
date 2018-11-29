package com.ouresports.grimstroke.app.base.vo;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/27
 */
public interface IVo<T> {
    T convertTo();

    IVo<T> convertFor(T t);

    List<IVo<T>> convertFor(List<T> t);
}
