package com.ouresports.grimstroke.core.entity.association;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.core.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 这是专题合集和信息流的多对多关系表
 * @author will
 * @date 2018/11/29
 */
@TableName("info_collections_informations")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain=true)
public class InfoCollectionsInformation extends BaseEntity {
    private Long infoCollectionId;
    private String informationType;
    private Long informationId;
}
