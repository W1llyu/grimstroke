package com.ouresports.grimstroke.base.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/12/17
 */
public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {
    <E> List<E> selectDtos(IPage<E> page, @Param("ew") Wrapper<T> wrapper);
}
