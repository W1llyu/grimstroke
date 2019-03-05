package com.ouresports.grimstroke.base.template;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.base.entity.ITo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/27
 */
@Data
public class PaginationTemplate<T> {
    private IMeta meta;
    protected List data;

    public PaginationTemplate(IPage<T> iPage) {
        data = iPage.getRecords();
        initMeta(iPage);
    }

    public PaginationTemplate(IPage<T> iPage, Class<? extends ITo<T>> voClass) throws IllegalAccessException, InstantiationException {
        data = voClass.newInstance().convertFor(iPage.getRecords());
        initMeta(iPage);
    }

    public PaginationTemplate(List<T> list, Class<? extends ITo<T>> voClass, IMeta meta) throws IllegalAccessException, InstantiationException {
        data = voClass.newInstance().convertFor(list);
        this.meta = meta;
    }

    private void initMeta(IPage iPage) {
        meta = MetaVo.builder()
                .currentPage(iPage.getCurrent())
                .totalCount(iPage.getTotal())
                .per(iPage.getSize())
                .build();
    }

    @Data
    @Builder
    static private class MetaVo implements IMeta {
        private long per;
        private long totalCount;
        private long currentPage;
    }
}
