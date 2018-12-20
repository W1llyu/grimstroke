package com.ouresports.grimstroke.info.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ouresports.grimstroke.base.template.ResultTemplate;
import com.ouresports.grimstroke.base.template.SingleTemplate;
import com.ouresports.grimstroke.info.entity.LiveStream;
import com.ouresports.grimstroke.info.rbo.admin.LiveStreamCallbackRbo;
import com.ouresports.grimstroke.info.service.LiveStreamService;
import com.ouresports.grimstroke.info.vo.admin.LiveStreamUrlVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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

    /**
     * 获得播放地址
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{id}/play_url")
    public ResponseEntity getPlayUrl(@PathVariable long id) throws Exception {
        String url = liveStreamService.getPlayUrl(liveStreamService.find(id));
        return render(new SingleTemplate<>(new LiveStreamUrlVo().setUrl(url)));
    }

    @GetMapping(value="/callback")
    public ResponseEntity callback(@Valid LiveStreamCallbackRbo rbo) throws Exception {
        LiveStream liveStream = liveStreamService.findBy(new QueryWrapper<LiveStream>().eq("id", Long.parseLong(rbo.getId())));
        if (liveStream != null) {
            if (Objects.equals(rbo.getAction(), "publish")) {
                liveStream.setActive(true);
            } else if (Objects.equals(rbo.getAction(), "publish_done")) {
                liveStream.setActive(false);
            }
        }
        liveStreamService.updateById(liveStream);
        return render(ResultTemplate.ok());
    }
}
