package com.ouresports.grimstroke.base.injector.methods;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.ouresports.grimstroke.base.injector.AbstractSoftMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.Map;

/**
 *
 * @author will
 * @date 2018/11/26
 */
public class SoftSelectMaps extends AbstractSoftMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.SELECT_MAPS;
        String sql = String.format(sqlMethod.getSql(), this.sqlSelectColumns(tableInfo, true), tableInfo.getTableName(), this.sqlWhereEntityWrapper(tableInfo));
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, modelClass);
        return this.addSelectMappedStatement(mapperClass, sqlMethod.getMethod(), sqlSource, Map.class, tableInfo);
    }
}
