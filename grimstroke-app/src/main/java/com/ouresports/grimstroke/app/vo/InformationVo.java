package com.ouresports.grimstroke.app.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.ouresports.grimstroke.app.base.vo.BaseVo;
import com.ouresports.grimstroke.core.dto.InformationDto;
import com.ouresports.grimstroke.core.enums.InformationSubType;
import com.ouresports.grimstroke.core.enums.InformationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/30
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InformationVo extends BaseVo<InformationDto> {
    private Long id;

    private InformationType type;

    private InformationSubType subType;

    private String title;

    @JSONField(serialize = false)
    private String coverImages;

    private List<String> coverImageList;

    private Integer gameId;

    private String tagName;

    private Long commentCount;

    private Long likeCount;

    private Boolean enabled;

    private Date createdAt;

    public void setCoverImages(String coverImages) {
        try {
            this.coverImages = coverImages;
            this.coverImageList = JSONArray.parseArray(coverImages, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
