package com.ouresports.grimstroke.app.rbo.admin;

import com.ouresports.grimstroke.core.base.entity.BaseTo;
import com.ouresports.grimstroke.core.entity.News;
import com.ouresports.grimstroke.core.enums.InformationSubType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 *
 * @author will
 * @date 2018/12/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NewsRbo extends BaseTo<News> {
    @NotNull(message="标题不可为空")
    private String title;
    @NotNull(message="封片图片不可为空")
    private String coverImage;
    @NotNull(message="类型不可为空")
    private InformationSubType type;
    private Integer gameId;
    private Long tagId;
    @NotNull(message="内容不可为空")
    private String content;
    private String author;
    private String source;
    private Long associateId;
    private Boolean sticky;
    private Boolean enabled;
}
