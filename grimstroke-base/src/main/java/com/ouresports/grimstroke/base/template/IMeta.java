package com.ouresports.grimstroke.base.template;

/**
 *
 * @author will
 * @date 2018/12/25
 */
public interface IMeta {
    long getPer();
    long getTotalCount();
    long getCurrentPage();
    void setPer(long per);
    void setTotalCount(long totalCount);
    void setCurrentPage(long currentPage);
}
