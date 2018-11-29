package com.ouresports.grimstroke.core.concern;

/**
 * 可作为被点赞的对象
 * @author will
 * @date 2018/11/29
 */
public interface Likable {
    String getLikableType();

    Long getId();
}
