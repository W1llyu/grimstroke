package com.ouresports.grimstroke.app.rbo.admin;

import com.ouresports.grimstroke.core.base.entity.BaseTo;
import com.ouresports.grimstroke.core.entity.Video;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 *
 * @author will
 * @date 2018/12/5
 */
@Data
@Accessors(chain=true)
public class VideoRbo extends BaseTo<Video> {
    private String title;
    private String coverImage;
    private String description;
    private Float duration;
    private Long size;
    @NotNull(message="vodId不可为空")
    private String vodId;
    @NotNull(message="游戏不可为空")
    private Integer gameId;
    @NotNull(message="标签不可为空")
    private Long tagId;
    private Boolean sticky;
    private Boolean enabled;

    public VideoRbo setTitleIfNull(String title) {
        if (this.title == null) {
            this.title = title;
        }
        return this;
    }

    public VideoRbo setCoverImageIfNull(String coverImage) {
        if (this.coverImage == null) {
            this.coverImage = coverImage;
        }
        return this;
    }

    public VideoRbo setDescriptionIfNull(String description) {
        if (this.description == null) {
            this.description = description;
        }
        return this;
    }
}
