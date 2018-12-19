package com.ouresports.grimstroke.info.dto;

import com.ouresports.grimstroke.info.enums.InformationType;
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
