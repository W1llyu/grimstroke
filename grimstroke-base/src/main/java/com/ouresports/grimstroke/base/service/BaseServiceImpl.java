package com.ouresports.grimstroke.base.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ouresports.grimstroke.base.mapper.BaseMapper;
import com.ouresports.grimstroke.base.util.CollectionUtil;
import com.ouresports.grimstroke.base.util.EntityUtil;
import com.ouresports.grimstroke.base.util.ReflectUtil;
import com.ouresports.grimstroke.base.util.WrapperUtil;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author will
 * @date 2018/12/17
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements Service<T> {
    protected String tableName;

    @Override
    public int count(T t) {
        QueryWrapper<T> wrapper = new QueryWrapper<>(t);
        WrapperUtil.appendEqualQuery(wrapper, wrapper.getEntity());
        return count(wrapper);
    }

    @Override
    public T find(long id) throws NotFoundException {
        T t = getById(id);
        if (t == null) {
            throw new NotFoundException("not found");
        }
        return t;
    }

    @Override
    public T findBy(QueryWrapper<T> wrapper) {
        if (wrapper == null) {
            wrapper = new QueryWrapper<>();
        }
        WrapperUtil.appendEqualQuery(wrapper, wrapper.getEntity());
        return baseMapper.selectOne(wrapper.last("LIMIT 1"));
    }

    @Override
    public T findBy(T t) {
        QueryWrapper<T> wrapper = new QueryWrapper<>(t);
        WrapperUtil.appendEqualQuery(wrapper, wrapper.getEntity());
        return baseMapper.selectOne(wrapper.last("LIMIT 1"));
    }

    @Override
    public T findOne(Wrapper<T> wrapper) throws TooManyResultsException {
        return getOne(wrapper);
    }

    @Override
    public IPage<T> list(IPage<T> var1, Wrapper<T> var2) {
        WrapperUtil.appendEqualQuery((QueryWrapper) var2, var2.getEntity());
        return page(var1, var2);
    }

    @Override
    public IPage<T> list(IPage<T> var1, T var2) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        WrapperUtil.appendEqualQuery(wrapper, var2);
        return page(var1, wrapper);
    }

    @Override
    public boolean save(T t) {
        initTimestamp(t, "createdAt");
        initTimestamp(t, "updatedAt");
        return super.save(t);
    }

    @Override
    public boolean saveBatch(Collection<T> collection) {
        for(T t: collection) {
            initTimestamp(t, "createdAt");
            initTimestamp(t, "updatedAt");
        }
        return super.saveBatch(collection);
    }

    @Override
    public boolean saveBatch(Collection<T> collection, int batchSize) {
        for(T t: collection) {
            initTimestamp(t, "createdAt");
            initTimestamp(t, "updatedAt");
        }
        return super.saveBatch(collection, batchSize);
    }

    @Override
    public boolean updateById(T t) {
        updateTimestamp(t, "updatedAt");
        return super.updateById(t);
    }

    @Override
    public boolean update(T t, Wrapper<T> wrapper) {
        updateTimestamp(t, "updatedAt");
        return super.update(t, wrapper);
    }

    @Override
    public boolean updateBatchById(Collection<T> collection) {
        for(T t: collection) {
            updateTimestamp(t, "updatedAt");
        }
        return super.updateBatchById(collection);
    }

    @Override
    public boolean updateBatchById(Collection<T> collection, int batchSize) {
        for(T t: collection) {
            updateTimestamp(t, "updatedAt");
        }
        return super.updateBatchById(collection, batchSize);
    }

    @Override
    @Transactional
    public T findOrCreateBy(T var1) {
        QueryWrapper<T> wrapper = new QueryWrapper<T>(var1);
        T t = findBy(wrapper);
        if (t == null) {
            t = wrapper.getEntity();
            save(t);
        }
        return t;
    }

    @Override
    public <E>E getDto(long id) throws NotFoundException {
        QueryWrapper<T> wrapper = new QueryWrapper<T>()
                .groupBy(String.format("`%s`.`id`", getTableName()))
                .last("limit 1")
                .eq(String.format("`%s`.`id`", getTableName()), id);
        return CollectionUtil.getFirstElement(baseMapper.selectDtos(null, wrapper));
    }

    @Override
    public <E> E getDto(QueryWrapper<T> wrapper) throws NotFoundException {
        wrapper.groupBy(String.format("`%s`.`id`", getTableName()))
                .last("LIMIT 1");
        WrapperUtil.appendEqualQuery(wrapper, wrapper.getEntity(), getTableName());
        return CollectionUtil.getFirstElement(baseMapper.selectDtos(null, wrapper));
    }

    @Override
    public <E> List<E> getDtos(QueryWrapper<T> wrapper) {
        wrapper.groupBy(String.format("`%s`.`id`", getTableName()));
        return baseMapper.selectDtos(null, wrapper);
    }

    @Override
    public <E>IPage<E> getDtos(IPage<E> page, QueryWrapper<T> wrapper) {
        wrapper.groupBy(String.format("`%s`.`id`", getTableName()));
        WrapperUtil.appendEqualQuery(wrapper, wrapper.getEntity(), getTableName());
        page.setRecords(baseMapper.selectDtos(page, wrapper));
        return page;
    }

    private void initTimestamp(T t, String fieldName) {
        try {
            if (ReflectUtil.getFieldValue(t, fieldName) == null) {
                updateTimestamp(t, fieldName);
            }
        } catch (NoSuchFieldException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTimestamp(T t, String fieldName) {
        try {
            ReflectUtil.setFieldValue(t, fieldName, new Date());
        } catch (NoSuchFieldException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTableName() {
        if (tableName == null) {
            Class<?> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            tableName = EntityUtil.getEntityTableName(entityClass);
        }
        return tableName;
    }
}
