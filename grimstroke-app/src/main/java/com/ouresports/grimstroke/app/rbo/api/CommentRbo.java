package com.ouresports.grimstroke.app.rbo.api;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author will
 * @date 2018/12/4
 */
@Data
public class CommentRbo {
    @NotNull(message="内容不可为空")
    @NotBlank(message="内容不可为空")
    private String content;
}
