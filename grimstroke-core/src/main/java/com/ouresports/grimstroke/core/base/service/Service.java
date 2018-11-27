package com.ouresports.grimstroke.core.base.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/22
 */
public interface Service<T> {
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
     * 根据 entity 条件，查询全部记录
     * </p>
     *
     * @param wrapper 实体对象封装操作类（可以为 null）
     * @return 实体集合
     */
    List<T> where(Wrapper<T> wrapper);

    /**
     * <p>
     * 查询分页记录
     * </p>
     *
     * @param iPage
     * @param wrapper
     * @return 分页集合
     */
    IPage<T> where(IPage<T> iPage, Wrapper<T> wrapper);

    /**
     * <p>
     * 查询（根据ID 批量查询）
     * </p>
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     * @return 实体集合
     */
    List<T> whereBatchIds(Collection<? extends Serializable> idList);

    /**
     * <p>
     * 根据 Wrapper 条件，查询总记录数
     * </p>
     *
     * @param wrapper 实体对象
     * @return 满足条件记录数
     */
    int count(Wrapper<T> wrapper);

    /**
     * <p>
     * 插入一条记录（选择字段，策略插入）
     * </p>
     *
     * @param t 实体对象
     */
    int create(T t);

    /**
     *
     * @param t 实体对象
     * @return 修改成功记录数
     */
    int updateById(T t);

    /**
     *
     * @param t       实体对象 (set 条件值,不能为 null)
     * @param wrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     * @return 修改成功记录数
     */
    int update(T t, Wrapper<T> wrapper);

    /**
     * <p>
     * 根据 ID 删除
     * </p>
     *
     * @param id 主键ID
     */
    int deleteById(long id);

    /**
     * <p>
     * 根据 entity 条件，删除记录
     * </p>
     *
     * @param wrapper 实体包装类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    int delete(Wrapper<T> wrapper);
}
