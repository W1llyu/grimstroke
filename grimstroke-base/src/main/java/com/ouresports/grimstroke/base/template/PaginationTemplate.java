package com.ouresports.grimstroke.base.template;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.base.entity.ITo;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/27
 */
@Data
public class PaginationTemplate<T> {
    @JSONField(name="meta")
    private MetaDto meta;
    @JSONField(name="data")
    protected List data;

    public PaginationTemplate(IPage<T> iPage) {
        data = iPage.getRecords();
        initMeta(iPage);
    }

    public PaginationTemplate(IPage<T> iPage, Class<? extends ITo<T>> voClass) throws IllegalAccessException, InstantiationException {
        data = voClass.newInstance().convertFor(iPage.getRecords());
        initMeta(iPage);
    }

    private void initMeta(IPage iPage) {
        meta = MetaDto.builder()
                .currentPage(iPage.getCurrent())
                .totalCount(iPage.getTotal())
                .per(iPage.getSize())
                .build();
    }

    @Getter
    @Builder
    static private class MetaDto {
        @JSONField(name="per")
        private long per;
        @JSONField(name="total_count")
        private long totalCount;
        @JSONField(name="current_page")
        private long currentPage;
    }
}
