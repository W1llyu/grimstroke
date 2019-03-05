package com.ouresports.grimstroke.info.controller.admin;

import com.ouresports.grimstroke.base.annotation.*;
import com.ouresports.grimstroke.base.template.ResultTemplate;
import com.ouresports.grimstroke.base.template.SingleTemplate;
import com.ouresports.grimstroke.info.rbo.admin.VideoRbo;
import com.ouresports.grimstroke.info.rbo.api.VideoAuthRbo;
import com.ouresports.grimstroke.info.rbo.admin.VideoUploadAuthRbo;
import com.ouresports.grimstroke.info.vo.admin.VideoVo;
import com.ouresports.grimstroke.info.entity.Video;
import com.ouresports.grimstroke.info.service.VideoService;
import com.ouresports.grimstroke.lib.aliyun.entity.VodDetailResponse;
import com.ouresports.grimstroke.lib.aliyun.entity.VodUploadAuthResponse;
import com.ouresports.grimstroke.lib.aliyun.service.AliyunVodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *
 * @author will
 * @date 2018/12/5
 */
@RestCrudController(value="AdminVideoController")
@RequestMapping(value="/admin/videos", produces="application/json;charset=UTF-8")
@RestIndex(dto=true, voClass=VideoVo.class)
@RestShow(dto=true, voClass=VideoVo.class)
@RestUpdate(rboClass=VideoRbo.class, valid=false)
@AuthToken
public class VideoController extends BaseController<Video, VideoService> {
    @Resource
    private AliyunVodService aliyunVodService;

    /**
     * 获得视频上传凭证
     * @param videoUploadAuthRbo
     * @return
     * @throws Exception
     */
    @PostMapping(value="/upload_auth")
    public ResponseEntity getUploadAuth(@Valid @RequestBody VideoUploadAuthRbo videoUploadAuthRbo)
            throws Exception {
        VodUploadAuthResponse vodUploadAuth = aliyunVodService.getVideoUploadAuth(videoUploadAuthRbo.convertTo());
        return render(new SingleTemplate<>(vodUploadAuth));
    }

    /**
     * 刷新视频上传凭证
     * @param rbo
     * @return
     * @throws Exception
     */
    @PostMapping(value="/refresh_upload_auth")
    public ResponseEntity refreshUploadAuth(@Valid @RequestBody VideoAuthRbo rbo) throws Exception {
        VodUploadAuthResponse vodUploadAuth = aliyunVodService.refreshUploadVideoAuth(rbo.getVideoId());
        return render(new SingleTemplate<>(vodUploadAuth));
    }

    /**
     * 创建一条视频记录
     * @param rbo
     * @return
     * @throws Exception
     */
    @PostMapping(value="")
    public ResponseEntity create(@Valid @RequestBody VideoRbo rbo) throws Exception {
        VodDetailResponse vodDetailResponse = aliyunVodService.getVideoDetail(rbo.getVodId());
        rbo.setTitleIfNull(vodDetailResponse.getVideo().getTitle())
                .setCoverImageIfNull(vodDetailResponse.getVideo().getCoverURL())
                .setDescriptionIfNull(vodDetailResponse.getVideo().getDescription())
                .setDuration(vodDetailResponse.getVideo().getDuration())
                .setSize(vodDetailResponse.getVideo().getSize());
        Video video = rbo.convertTo();
        baseService.save(video);
        return render(ResultTemplate.createOk());
    }
}
