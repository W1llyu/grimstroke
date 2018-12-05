package com.ouresports.grimstroke.app.controller.admin;

import com.ouresports.grimstroke.app.base.template.ResultTemplate;
import com.ouresports.grimstroke.app.base.template.SingleTemplate;
import com.ouresports.grimstroke.app.rbo.admin.VideoRbo;
import com.ouresports.grimstroke.app.rbo.api.VideoAuthRbo;
import com.ouresports.grimstroke.app.rbo.admin.VideoUploadAuthRbo;
import com.ouresports.grimstroke.core.entity.Video;
import com.ouresports.grimstroke.core.service.VideoService;
import com.ouresports.grimstroke.lib.aliyun.entity.VodDetailResponse;
import com.ouresports.grimstroke.lib.aliyun.entity.VodUploadAuthResponse;
import com.ouresports.grimstroke.lib.aliyun.service.AliyunVodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *
 * @author will
 * @date 2018/12/5
 */
@RestController(value = "AdminVideoController")
@RequestMapping(value="/admin/videos", produces="application/json;charset=UTF-8")
public class VideoController extends BaseController {
    @Resource
    private AliyunVodService aliyunVodService;
    @Resource
    private VideoService videoService;

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
     * @param videoRbo
     * @return
     * @throws Exception
     */
    @PostMapping(value="")
    public ResponseEntity create(@Valid @RequestBody VideoRbo videoRbo) throws Exception {
        VodDetailResponse vodDetailResponse = aliyunVodService.getVideoDetail(videoRbo.getVodId());
        videoRbo.setTitleIfNull(vodDetailResponse.getVideo().getTitle())
                .setCoverImageIfNull(vodDetailResponse.getVideo().getCoverURL())
                .setDescriptionIfNull(vodDetailResponse.getVideo().getDescription())
                .setDuration(vodDetailResponse.getVideo().getDuration())
                .setSize(vodDetailResponse.getVideo().getSize());
        Video video = videoRbo.convertTo();
        videoService.save(video);
        return render(ResultTemplate.createOk());
    }
}
