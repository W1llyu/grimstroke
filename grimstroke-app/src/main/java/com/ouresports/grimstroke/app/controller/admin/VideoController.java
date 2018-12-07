package com.ouresports.grimstroke.app.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.app.base.template.PaginationTemplate;
import com.ouresports.grimstroke.app.base.template.ResultTemplate;
import com.ouresports.grimstroke.app.base.template.SingleTemplate;
import com.ouresports.grimstroke.app.rbo.admin.VideoRbo;
import com.ouresports.grimstroke.app.rbo.api.VideoAuthRbo;
import com.ouresports.grimstroke.app.rbo.admin.VideoUploadAuthRbo;
import com.ouresports.grimstroke.app.vo.admin.VideoVo;
import com.ouresports.grimstroke.core.dto.VideoDto;
import com.ouresports.grimstroke.core.entity.Video;
import com.ouresports.grimstroke.core.service.VideoService;
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
@RestController(value="AdminVideoController")
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
     * 列表
     * @param currentPage
     * @param per
     * @param gameId
     * @return
     * @throws Exception
     */
    @GetMapping(value="")
    public ResponseEntity index(@RequestParam(value="page", defaultValue="1") int currentPage,
                                @RequestParam(defaultValue="10") int per,
                                @RequestParam(value="game_id", required=false) Integer gameId) throws Exception {
        Page<VideoDto> page = new Page<>(currentPage, per);
        Video video = new Video().setGameId(gameId);
        return render(new PaginationTemplate<>(videoService.getDtos(page, new QueryWrapper<>(video)), VideoVo.class));
    }

    /**
     * 详情
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{id}")
    public ResponseEntity show(@PathVariable long id) throws Exception {
        VideoDto dto = videoService.getDto(id);
        return render(new SingleTemplate<>(dto, VideoVo.class));
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
        videoService.save(video);
        return render(ResultTemplate.createOk());
    }

    /**
     * 更新
     * @param id
     * @param rbo
     * @return
     * @throws Exception
     */
    @PatchMapping(value="/{id}")
    public ResponseEntity update(@PathVariable long id,
                                 @RequestBody VideoRbo rbo) throws Exception {
        Video video = rbo.convertTo();
        video.setId(id);
        videoService.updateById(video);
        return render(ResultTemplate.updateOk());
    }
}
