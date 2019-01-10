package com.ouresports.grimstroke.info.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ouresports.grimstroke.base.template.ResultTemplate;
import com.ouresports.grimstroke.base.template.SingleTemplate;
import com.ouresports.grimstroke.info.entity.LiveStream;
import com.ouresports.grimstroke.info.enums.StreamTemplate;
import com.ouresports.grimstroke.info.service.LiveStreamService;
import com.ouresports.grimstroke.info.vo.admin.LiveStreamUrlVo;
import com.ouresports.grimstroke.lib.aliyun.service.AliyunLiveStreamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 *
 * @author will
 * @date 2018/12/20
 */
@RestController
@RequestMapping(value="/api/live_streams", produces="application/json;charset=UTF-8")
public class LiveStreamController extends BaseController {
    @Resource
    private LiveStreamService liveStreamService;
    @Resource
    private AliyunLiveStreamService aliyunLiveStreamService;

    /**
     * 获得播放地址
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{id}/play_url")
    public ResponseEntity getPlayUrl(@PathVariable long id,
                                     @RequestParam(defaultValue="LSD") StreamTemplate template) throws Exception {
        String url = liveStreamService.getPlayUrl(liveStreamService.find(id), template);
        return render(new SingleTemplate<>(new LiveStreamUrlVo().setUrl(url)));
    }

    /**
     * aliyun直播的回调
     * @param id
     * @param action
     * @return
     * @throws Exception
     */
    @GetMapping(value="/callback")
    public ResponseEntity callback(@RequestParam long id,
                                   @RequestParam String action) throws Exception {
        LiveStream liveStream = liveStreamService.findBy(new QueryWrapper<LiveStream>().eq("id", id));
        if (liveStream != null) {
            if (Objects.equals(action, "publish")) {
                liveStream.setActive(true);
            } else if (Objects.equals(action, "publish_done")) {
                liveStream.setActive(false);
            }
        }
        liveStreamService.updateById(liveStream);
        return render(ResultTemplate.ok());
    }

    /**
     * 获得通知信息
     * @return
     * @throws Exception
     */
    @GetMapping(value="/get_notifyinfo")
    public ResponseEntity getNotifyinfo() throws Exception {
        return render(new SingleTemplate<>(aliyunLiveStreamService.getLiveStreamNotifyUrl().getLiveStreamsNotifyConfig()));
    }
}
