package com.ouresports.grimstroke.core.base.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/22
 */
public interface Service<T> extends IService<T> {
    /**
     * <p>
     * 根据 ID 查询
     * </p>
     *
     * @param id 主键ID
     * @return 实体
     */
    T find(long id);

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
}
