package com.ouresports.grimstroke.app.rbo.admin;

import com.ouresports.grimstroke.core.base.entity.BaseTo;
import com.ouresports.grimstroke.core.concern.InfoCollectionable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author will
 * @date 2018/12/8
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InformationRbo extends BaseTo<InfoCollectionable> {
    @NotNull(message="信息流类型不可为空")
    @NotBlank(message="信息流类型不可为空")
    private String informationType;
    @NotNull(message="信息流id不可为空")
    private Long informationId;

    @Override
    public InfoCollectionable convertTo() {
        return new InfoCollectionable() {
            @Override
            public String getInfoCollectionableType() {
                return informationType;
            }

            @Override
            public Long getId() {
                return informationId;
            }
        };
    }
}
