package com.ouresports.grimstroke.core.base.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.util.ReflectUtil;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/22
 */
public class BaseServiceImpl<T> implements Service<T> {
    @Autowired
    protected BaseMapper<T> mapper;

    @Override
    public T find(long id) {
        return mapper.selectById(id);
    }

    @Override
    public T findBy(QueryWrapper<T> wrapper) {
        if (wrapper == null) {
            wrapper = new QueryWrapper<>();
        }
        return mapper.selectOne(wrapper.last("LIMIT 1"));
    }

    @Override
    public T findOne(Wrapper<T> wrapper) throws TooManyResultsException {
        return mapper.selectOne(wrapper);
    }

    @Override
    public List<T> whereBatchIds(Collection<? extends Serializable> idList) {
        return mapper.selectBatchIds(idList);
    }

    @Override
    public List<T> where(Wrapper<T> wrapper) {
        return mapper.selectList(wrapper);
    }

    @Override
    public IPage<T> where(IPage<T> iPage, Wrapper<T> wrapper) {
        return mapper.selectPage(iPage, wrapper);
    }

    @Override
    public int count(Wrapper<T> wrapper) {
        return mapper.selectCount(wrapper);
    }

    @Override
    public int create(T t) {
        initTimestamp(t, "createdAt");
        initTimestamp(t, "updatedAt");
        return mapper.insert(t);
    }

    @Override
    public int updateById(T t) {
        initTimestamp(t, "updatedAt");
        return mapper.updateById(t);
    }

    @Override
    public int update(T t, Wrapper<T> wrapper) {
        initTimestamp(t, "updatedAt");
        return mapper.update(t, wrapper);
    }

    @Override
    public int deleteById(long id) {
        return mapper.deleteById(id);
    }

    @Override
    public int delete(Wrapper<T> wrapper) {
        return mapper.delete(wrapper);
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
}
