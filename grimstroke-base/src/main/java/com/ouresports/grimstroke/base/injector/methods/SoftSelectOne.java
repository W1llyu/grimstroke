package com.ouresports.grimstroke.base.injector.methods;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.ouresports.grimstroke.base.injector.AbstractSoftMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 *
 * @author will
 * @date 2018/11/26
 */
public class SoftSelectOne extends AbstractSoftMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.SELECT_ONE;
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, String.format(sqlMethod.getSql(), new Object[]{this.sqlSelectColumns(tableInfo, true), tableInfo.getTableName(), this.sqlWhereEntityWrapper(tableInfo)}), modelClass);
        return this.addSelectMappedStatement(mapperClass, sqlMethod.getMethod(), sqlSource, modelClass, tableInfo);
    }
}
