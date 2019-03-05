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
public class SoftDeleteByMap extends AbstractSoftMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.LOGIC_DELETE_BY_MAP;
        String sql;
        if(tableInfo.isLogicDelete()) {
            sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), this.sqlLogicSet(tableInfo), this.sqlWhereByMap(tableInfo));
        } else {
            sqlMethod = SqlMethod.DELETE_BY_MAP;
            sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), this.sqlWhereByMap(tableInfo));
        }

        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, Map.class);
        return this.addUpdateMappedStatement(mapperClass, Map.class, sqlMethod.getMethod(), sqlSource);
    }
}
