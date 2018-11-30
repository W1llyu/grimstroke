package com.ouresports.grimstroke.core.util;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/30
 */
public class ListUtil {
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
}
