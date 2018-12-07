package com.ouresports.grimstroke.core.util;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 *
 * @author will
 * @date 2018/12/7
 */
public class EntityUtil {
    public static String getEntityTableName(Class<?> claz) {
        TableName tableName = claz.getAnnotation(TableName.class);
        if (tableName == null) {
            return StringUtil.camelhumpToUnderline(claz.getSimpleName());
        } else {
            return tableName.value();
        }
    }
}
