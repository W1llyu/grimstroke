package com.ouresports.grimstroke.app.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.ouresports.grimstroke.app.base.vo.BaseVo;
import com.ouresports.grimstroke.core.entity.News;
import com.ouresports.grimstroke.core.enums.Game;
import com.ouresports.grimstroke.core.enums.NewsType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/11/27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain=true)
public class NewsVo extends BaseVo<News> {
    private long id;
    private String title;
    private NewsType type;
    private Game game;

    private Long commentCount;
    @JSONField(name="created_at", format="yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
}
