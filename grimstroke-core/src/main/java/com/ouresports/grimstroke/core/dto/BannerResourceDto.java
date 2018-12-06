package com.ouresports.grimstroke.core.dto;

import com.ouresports.grimstroke.core.enums.InformationType;
import lombok.Data;

/**
 *
 * @author will
 * @date 2018/12/6
 */
@Data
public class BannerResourceDto {
    private InformationType type;
    private Long id;
    private String title;
    private String coverImage;
}
