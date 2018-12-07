package com.ouresports.grimstroke.core.base.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ouresports.grimstroke.core.base.mapper.BaseMapper;
import com.ouresports.grimstroke.core.util.CollectionUtil;
import com.ouresports.grimstroke.core.util.EntityUtil;
import com.ouresports.grimstroke.core.util.ReflectUtil;
import com.ouresports.grimstroke.core.util.WrapperUtil;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author will
 * @date 2018/11/28
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements Service<T> {
    protected Class<?> entityClass;

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
        return baseMapper.selectOne(wrapper.last("LIMIT 1"));
    }

    @Override
    public T findOne(Wrapper<T> wrapper) throws TooManyResultsException {
        return getOne(wrapper);
    }

    @Override
    public IPage<T> list(IPage<T> var1, Wrapper<T> var2) {
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
        String tableName = EntityUtil.getEntityTableName(getEntityClass());
        QueryWrapper<T> wrapper = new QueryWrapper<T>()
                .groupBy(String.format("`%s`.`id`", tableName))
                .last("limit 1")
                .eq(String.format("`%s`.`id`", tableName), id);
        return CollectionUtil.getFirstElement(baseMapper.selectDtos(null, wrapper));
    }

    @Override
    public <E>IPage<E> getDtos(IPage<E> page, QueryWrapper<T> wrapper) {
        String tableName = EntityUtil.getEntityTableName(getEntityClass());
        wrapper.groupBy(String.format("`%s`.`id`", tableName));
        WrapperUtil.appendEqualQuery(wrapper, wrapper.getEntity(), tableName);
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

    private Class<?> getEntityClass() {
        if (entityClass == null) {
            entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        }
        return entityClass;
    }
}
