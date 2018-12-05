package com.ouresports.grimstroke.core.entity.association;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.core.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 这是用户和信息流的多对多关系表
 * @author will
 * @date 2018/11/28
 */
@TableName("users_informations")
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain=true)
public class UsersInformation extends BaseEntity {
    private Long userId;
    private String informationType;
    private Long informationId;
}
