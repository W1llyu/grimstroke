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
public class SoftSelectBatchByIds extends AbstractSoftMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.LOGIC_SELECT_BATCH_BY_IDS;
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, String.format(sqlMethod.getSql(), new Object[]{this.sqlSelectColumns(tableInfo, false), tableInfo.getTableName(), tableInfo.getKeyColumn(), SqlScriptUtils.convertForeach("#{item}", "coll", (String)null, "item", ","), getQuerySoftDeleteSql(tableInfo, true)}), modelClass);
        return this.addSelectMappedStatement(mapperClass, sqlMethod.getMethod(), sqlSource, modelClass, tableInfo);
    }
}
