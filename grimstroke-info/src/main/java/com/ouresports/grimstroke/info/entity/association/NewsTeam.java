package com.ouresports.grimstroke.info.entity.association;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ouresports.grimstroke.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author will
 * @date 2018/12/17
 */
@TableName("news_teams")
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain=true)
public class NewsTeam extends BaseEntity {
    private Long newsId;
    private Long teamId;
}
