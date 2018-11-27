package com.ouresports.grimstroke.app.base.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PaginationDto<T> extends BaseDto<T> {
    @JSONField(name="meta")
    private MetaDto meta;
    @JSONField(name="data")
    protected List data;

    public PaginationDto(IPage<T> iPage) {
        data = iPage.getRecords();
        initMeta(iPage);
    }

    public PaginationDto(IPage<T> iPage, Class<? extends IDto> dtoClass) throws IllegalAccessException, InstantiationException {
        data = dtoClass.newInstance().convertFor(iPage.getRecords());
        initMeta(iPage);
    }

    private void initMeta(IPage<T> iPage) {
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
