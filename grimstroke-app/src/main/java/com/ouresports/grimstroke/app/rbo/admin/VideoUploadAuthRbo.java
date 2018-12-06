package com.ouresports.grimstroke.app.rbo.admin;

import com.ouresports.grimstroke.core.base.entity.BaseTo;
import com.ouresports.grimstroke.lib.aliyun.entity.VodUploadInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 *
 * @author will
 * @date 2018/12/5
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VideoUploadAuthRbo extends BaseTo<VodUploadInfo>{
    @NotNull(message="标题不可为空")
    private String title;
    @NotNull(message="文件名不可为空")
    private String fileName;
}
