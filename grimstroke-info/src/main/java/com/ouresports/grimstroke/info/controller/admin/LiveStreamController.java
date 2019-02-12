package com.ouresports.grimstroke.info.controller.admin;

import com.ouresports.grimstroke.base.annotation.*;
import com.ouresports.grimstroke.base.template.ResultTemplate;
import com.ouresports.grimstroke.base.template.SingleTemplate;
import com.ouresports.grimstroke.info.entity.LiveStream;
import com.ouresports.grimstroke.info.rbo.admin.ExternLiveStreamRbo;
import com.ouresports.grimstroke.info.rbo.admin.LiveStreamRbo;
import com.ouresports.grimstroke.info.service.LiveStreamService;
import com.ouresports.grimstroke.info.service.SeriesStreamService;
import com.ouresports.grimstroke.info.vo.admin.LiveStreamUrlVo;
import com.ouresports.grimstroke.info.vo.admin.LiveStreamVo;
import com.ouresports.grimstroke.lib.livestream.entity.LivestreamSyncRbo;
import com.ouresports.grimstroke.lib.livestream.service.LivestreamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *
 * @author will
 * @date 2018/12/19
 */
@RestCrudController(value="AdminLiveStreamController")
@RequestMapping(value="/admin/live_streams", produces="application/json;charset=UTF-8")
@RestIndex(dto=true, voClass=LiveStreamVo.class)
@RestShow(dto=true, voClass=LiveStreamVo.class)
@RestUpdate(rboClass=LiveStreamRbo.class, valid=false)
@AuthToken
public class LiveStreamController extends BaseController<LiveStream, LiveStreamService> {
    @Resource
    private SeriesStreamService seriesStreamService;
    @Resource
    private LivestreamService livestreamService;

    /**
     * 创建直播流
     * @param rbo
     * @return
     * @throws Exception
     */
    @PostMapping(value="")
    public ResponseEntity create(@Valid @RequestBody LiveStreamRbo rbo) throws Exception {
        LiveStream liveStream = rbo.convertTo();
        baseService.save(liveStream);
        seriesStreamService.addSeries(liveStream, rbo.getMatchSeriesId());
        return render(ResultTemplate.createOk());
    }

    /**
     * 开始官方直播流
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping(value="/{id}/start")
    public ResponseEntity start(@PathVariable long id,
                                @Valid @RequestBody ExternLiveStreamRbo rbo) throws Exception {
        LiveStream liveStream = baseService.find(id);
        LivestreamSyncRbo livestreamSyncRbo = new LivestreamSyncRbo()
                .setId(liveStream.getId().toString())
                .setRoomId(rbo.getRoomId())
                .setPlatform(rbo.getPlatform())
                .setRtmp(baseService.getPushUrl(liveStream));
        livestreamService.createLivestreamSync(livestreamSyncRbo);
        liveStream.setPlatform(rbo.getPlatform());
        baseService.updateById(liveStream);
        return render(ResultTemplate.updateOk());
    }

    /**
     * 停止官方直播流
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping(value="/{id}/stop")
    public ResponseEntity stop(@PathVariable long id) throws Exception {
        LiveStream liveStream = baseService.find(id);
        livestreamService.deleteLivestreamSync(liveStream.getId().toString());
        return render(ResultTemplate.ok());
    }

    /**
     * 获得推流地址
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{id}/push_url")
    public ResponseEntity getPushUrl(@PathVariable long id) throws Exception {
        String url = baseService.getPushUrl(baseService.find(id));
        return render(new SingleTemplate<>(new LiveStreamUrlVo().setUrl(url)));
    }
}
