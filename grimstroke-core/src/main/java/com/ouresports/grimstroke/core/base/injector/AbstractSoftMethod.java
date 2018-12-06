package com.ouresports.grimstroke.core.base.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author will
 * @date 2018/11/26
 */
public abstract class AbstractSoftMethod extends AbstractMethod {
    protected String sqlLogicSet(TableInfo table) {
        return "SET " + getUpdateSoftDeleteSql(table, false, true);
    }

    @Override
    protected String sqlWhereEntityWrapper(TableInfo table) {
        if(table.isLogicDelete()) {
            String sqlScript = table.getAllSqlWhere(true, true, "ew.entity.");
            sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", "ew.entity"), true);
            sqlScript = sqlScript + "\n" + getQuerySoftDeleteSql(table, true) + "\n";
            sqlScript = sqlScript + SqlScriptUtils.convertIf(String.format(" AND ${%s}", "ew.sqlSegment"), String.format("%s!=null and %s!=\'\'", "ew.sqlSegment", "ew.sqlSegment"), false);
            sqlScript = SqlScriptUtils.convertTrim(sqlScript, "WHERE", null, "AND|OR", (String)null);
            sqlScript = SqlScriptUtils.convertChoose("ew!=null and !ew.emptyOfWhere", sqlScript, "WHERE " + getQuerySoftDeleteSql(table, false));
            return sqlScript;
        } else {
            return super.sqlWhereEntityWrapper(table);
        }
    }

    @Override
    protected String sqlWhereByMap(TableInfo table) {
        if(table.isLogicDelete()) {
            String sqlScript = SqlScriptUtils.convertChoose("v == null", " ${k} IS NULL ", " ${k} = #{v} ");
            sqlScript = SqlScriptUtils.convertForeach(sqlScript, "cm", "k", "v", "AND");
            sqlScript = SqlScriptUtils.convertIf(sqlScript, "cm != null and !cm.isEmpty", true);
            sqlScript = sqlScript + "\n" + getQuerySoftDeleteSql(table, true);
            sqlScript = SqlScriptUtils.convertTrim(sqlScript, "WHERE", null, "AND", (String)null);
            return sqlScript;
        } else {
            return super.sqlWhereByMap(table);
        }
    }

    protected String getUpdateSoftDeleteSql(TableInfo table, boolean startWithAnd, boolean deleteValue) {
        if(table.isLogicDelete()) {
            TableFieldInfo field = table.getFieldList().stream().filter(TableFieldInfo::isLogicDelete).findFirst().orElseThrow(
                    () -> ExceptionUtils.mpe(String.format("can\'t find the logicFiled from table {%s}", new Object[]{table.getTableName()})
                    ));
            String formatStr = deleteValue? "\'%s\'" : "%s";
            String logicDeleteSql = field.getColumn() + "=" + String.format(formatStr, deleteValue ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) : "null");
            if(startWithAnd) {
                logicDeleteSql = " AND " + logicDeleteSql;
            }

            return logicDeleteSql;
        } else {
            return "";
        }
    }

    protected String getQuerySoftDeleteSql(TableInfo table, boolean startWithAnd) {
        if(table.isLogicDelete()) {
            TableFieldInfo field = table.getFieldList().stream().filter(TableFieldInfo::isLogicDelete).findFirst().orElseThrow(
                    () -> ExceptionUtils.mpe(String.format("can\'t find the logicFiled from table {%s}", new Object[]{table.getTableName()})
                    ));
            String logicDeleteSql = field.getColumn() + " IS NULL";
            if(startWithAnd) {
                logicDeleteSql = " AND " + logicDeleteSql;
            }

            return logicDeleteSql;
        } else {
            return "";
        }
    }
}
