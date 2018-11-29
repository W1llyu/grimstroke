package com.ouresports.grimstroke.core.concern;

/**
 * 可作为被评论的对象
 * @author will
 * @date 2018/11/27
 */
public interface Commentable {
    String getCommentableType();

    Long getId();
}
