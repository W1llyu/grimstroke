package com.ouresports.grimstroke.info.vo.api.target;

import com.ouresports.grimstroke.info.dto.NormalUserDto;
import lombok.Data;

/**
 *
 * @author will
 * @date 2018/12/17
 */
@Data
public class CommentDtoTargetVo {
    private Long id;
    private String content;
    private NormalUserDto user;
}
