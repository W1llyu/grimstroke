package com.ouresports.grimstroke.base.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.baomidou.mybatisplus.core.injector.methods.*;
import com.ouresports.grimstroke.base.injector.methods.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author will
 * @date 2018/11/26
 */
public class ApplicationInjector extends AbstractSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList() {
        return Stream.of(
                new Insert(),
                new SoftDelete(),
                new SoftDeleteByMap(),
                new SoftDeleteById(),
                new SoftDeleteBatchByIds(),
                new SoftUpdate(),
                new SoftUpdateById(),
                new SoftSelectById(),
                new SoftSelectBatchByIds(),
                new SoftSelectByMap(),
                new SoftSelectOne(),
                new SoftSelectCount(),
                new SoftSelectMaps(),
                new SoftSelectMapsPage(),
                new SoftSelectObjs(),
                new SoftSelectList(),
                new SoftSelectPage()
        ).collect(Collectors.toList());
    }
}
