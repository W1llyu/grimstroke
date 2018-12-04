package com.ouresports.grimstroke.core.util;

import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 *
 * @author will
 * @date 2018/11/30
 */
public class CollectionUtil {
    /**
     * 安全地在把元素插入到list指定位置
     * @param list
     * @param element
     * @param index
     * @param <T>
     * @return
     */
    public static <T>int addElement(List<T> list, T element, int index) {
        if (index > list.size()) {
            // 超出list长度时插入到末尾；
            list.add(element);
            return list.size() - 1;
        } else {
            // 在指定位置插入；
            list.add(index, element);
            return index;
        }
    }

    public static <T, E> Set<T> getElementAttrsOfList(Collection<E> collection, String propertyName) throws NoSuchFieldException, IllegalAccessException {
        Set<T> results = Sets.newHashSet();
        for (E element: collection) {
            results.add((T) ReflectUtil.getFieldValue(element, propertyName));
        }
        return results;
    }
}
