package com.ouresports.grimstroke.app.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.ouresports.grimstroke.app.base.dto.BaseDto;
import com.ouresports.grimstroke.app.base.dto.IDto;
import com.ouresports.grimstroke.core.entity.News;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 *
 * @author will
 * @date 2018/11/27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain=true)
public class NewsDto extends BaseDto<News>{
    private long id;
    private String title;
    @JSONField(name="created_at", format="yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @Override
    public IDto<News> convertFor(News news) {
        NewsDto dto = new NewsDto();
        BeanUtils.copyProperties(news, dto);
        return dto;
    }
}
