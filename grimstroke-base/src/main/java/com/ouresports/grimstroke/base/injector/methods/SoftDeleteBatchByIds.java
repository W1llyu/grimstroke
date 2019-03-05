package com.ouresports.grimstroke.base.injector.methods;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import com.ouresports.grimstroke.base.injector.AbstractSoftMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 *
 * @author will
 * @date 2018/11/26
 */
public class SoftDeleteBatchByIds extends AbstractSoftMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.LOGIC_DELETE_BATCH_BY_IDS;
        String sql;
        if(tableInfo.isLogicDelete()) {
            sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), this.sqlLogicSet(tableInfo), tableInfo.getKeyColumn(), SqlScriptUtils.convertForeach("#{item}", "coll", (String)null, "item", ","), getQuerySoftDeleteSql(tableInfo, true));
        } else {
            sqlMethod = SqlMethod.DELETE_BATCH_BY_IDS;
            sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), tableInfo.getKeyColumn(), SqlScriptUtils.convertForeach("#{item}", "coll", (String)null, "item", ","));
        }

        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, modelClass);
        return this.addUpdateMappedStatement(mapperClass, modelClass, sqlMethod.getMethod(), sqlSource);
    }
}
