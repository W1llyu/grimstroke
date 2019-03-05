package com.ouresports.grimstroke.base.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/12/17
 */
public interface Service<T> extends IService<T> {
    /**
     *
     * @param t
     * @return
     */
    int count(T t);

    /**
     * <p>
     * 根据 ID 查询
     * </p>
     *
     * @param id 主键ID
     * @return 实体
     */
    T find(long id) throws NotFoundException;

    /**
     * <p>
     * 根据 entity 条件，查询一条记录
     * </p>
     *
     * @param wrapper 实体对象
     * @return 实体
     */
    T findBy(QueryWrapper<T> wrapper);

    /**
     * <p>
     * 根据 entity 条件，查询一条记录
     * </p>
     *
     * @param t
     * @return
     */
    T findBy(T t);

    /**
     * <p>
     * 根据 entity 条件，查询一条记录
     * </p>
     *
     * @param wrapper
     * @return 实体
     * @throws TooManyResultsException
     */
    T findOne(Wrapper<T> wrapper) throws TooManyResultsException;

    /**
     * <p>
     * </p>
     *
     * @param var1
     * @return
     */
    T findOrCreateBy(T var1);

    /**
     * <p>
     * 分页查询
     * </p>
     *
     * @param var1
     * @param var2
     * @return
     */
    IPage<T> list(IPage<T> var1, Wrapper<T> var2);

    /**
     * <p>
     * 分页查询
     * </p>
     *
     * @param var1
     * @param var2
     * @return
     */
    IPage<T> list(IPage<T> var1, T var2);

    <E>E getDto(long id) throws NotFoundException;

    <E>E getDto(QueryWrapper<T> wrapper) throws NotFoundException;

    <E>List<E> getDtos(QueryWrapper<T> wrapper);

    <E>IPage<E> getDtos(IPage<E> page, QueryWrapper<T> wrapper);
}
