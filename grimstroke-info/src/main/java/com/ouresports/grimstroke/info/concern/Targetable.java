package com.ouresports.grimstroke.info.concern;

/**
 *
 * @author will
 * @date 2018/12/13
 */
public interface Targetable {
    String getTargetType();
    Long getTargetId();
    void setTarget(Object target);
}
