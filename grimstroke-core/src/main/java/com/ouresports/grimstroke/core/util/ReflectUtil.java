package com.ouresports.grimstroke.core.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

/**
 *
 * @author will
 * @date 2018/11/26
 */
public class ReflectUtil {
    public static List<Field> getRecurseFields(Class<?> claz) {
        List<Field> fieldList = Lists.newArrayList();
        Class<?> tempClass = claz;
        while (tempClass != null && !tempClass.equals(Object.class)) {
            Field[] fields = tempClass.getDeclaredFields();
            int index = 0;
            for (Field field: fields) {
                // 排除静态字段
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                if (tempClass != claz) {
                    fieldList.add(index, field);
                    index++;
                } else {
                    fieldList.add(field);
                }
            }
            tempClass = tempClass.getSuperclass();
        }
        return fieldList;
    }

    public static <T>Map<String, Object> getNonNullAttributes(T t) {
        Map<String, Object> attrMap = Maps.newHashMap();
        Class<?> tempClass = t.getClass();
        while (tempClass != null && !tempClass.equals(Object.class)) {
            Field[] fields = tempClass.getDeclaredFields();
            for (Field field: fields) {
                // 排除静态字段
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                try {
                    Object value = getFieldValue(t, field.getName());
                    if (value != null) {
                        attrMap.put(field.getName(), value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            tempClass = tempClass.getSuperclass();
        }
        return attrMap;
    }

    /**
     * 获取Class的包括所有祖先类在内的所有field
     * @param claz
     * @param name
     * @return
     */
    public static Field getRecurseField(Class claz, String name) throws NoSuchFieldException {
        Field field = null;
        Class tempClass = claz;
        while (tempClass != null) {
            try {
                field = tempClass.getDeclaredField(name);
                if (field != null) {
                    break;
                }
            } catch (Exception e) {
                tempClass = tempClass.getSuperclass();
            }
        }
        if (field == null) {
            throw new NoSuchFieldException(name);
        }
        return field;
    }

    public static Object getFieldValue(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = getRecurseField(obj.getClass(), fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }

    /**
     * 修改实例的某个field的值
     * @param obj
     * @param fieldName
     * @param value
     */
    public static void setFieldValue(Object obj, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        if (obj == null) {
            return;
        }
        Field field = getRecurseField(obj.getClass(), fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }
}
