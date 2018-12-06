package com.ouresports.grimstroke.core.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 *
 * @author will
 * @date 2018/12/6
 */
public class WrapperUtil {
    public static <T>void appendEqualQuery(QueryWrapper wrapper, T t, String prefix) {
        if (t == null) {
            return;
        }
        ReflectUtil.getNonNullAttributes(t).forEach((k, v) ->{
            wrapper.eq(String.format("`%s`.`%s`", prefix, StringUtil.camelhumpToUnderline(k)), v);
        });
    }

    public static <T>void appendEqualQuery(QueryWrapper wrapper, T t) {
        if (t == null) {
            return;
        }
        ReflectUtil.getNonNullAttributes(t).forEach((k, v) ->{
            wrapper.eq(String.format("`%s`", StringUtil.camelhumpToUnderline(k)), v);
        });
    }
}
