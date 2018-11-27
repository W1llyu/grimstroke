package com.ouresports.grimstroke.core.util;

import java.lang.reflect.Field;

/**
 *
 * @author will
 * @date 2018/11/26
 */
public class ReflectUtil {
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
