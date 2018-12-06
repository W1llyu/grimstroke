package com.ouresports.grimstroke.app.vo.api;

import com.ouresports.grimstroke.core.base.entity.BaseTo;
import com.ouresports.grimstroke.core.dto.BannerDto;
import com.ouresports.grimstroke.core.enums.InformationType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author will
 * @date 2018/12/5
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BannerVo extends BaseTo<BannerDto> {
    private String title;
    private String coverImage;
    private BannerResourceVo resource;
    private String redirectUrl;

    @Data
    private static class BannerResourceVo {
        private Long id;
        private InformationType type;
    }

    @Override
    public BannerVo convertFor(BannerDto dto) {
        BannerVo vo = new BannerVo();
        BeanUtils.copyProperties(dto, vo);
        if (dto.getResource() == null) {
            return vo;
        }
        if (vo.getTitle() == null) {
            vo.setTitle(dto.getResource().getTitle());
        }
        if (vo.getCoverImage() == null) {
            vo.setCoverImage(dto.getResource().getCoverImage());
        }
        BannerResourceVo resourceVo = new BannerResourceVo();
        BeanUtils.copyProperties(dto.getResource(), resourceVo);
        vo.setResource(resourceVo);
        return vo;
    }
}
