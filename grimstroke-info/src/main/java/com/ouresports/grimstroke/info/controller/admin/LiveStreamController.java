package com.ouresports.grimstroke.info.controller.admin;

import com.ouresports.grimstroke.base.annotation.*;
import com.ouresports.grimstroke.base.template.ResultTemplate;
import com.ouresports.grimstroke.base.template.SingleTemplate;
import com.ouresports.grimstroke.info.entity.LiveStream;
import com.ouresports.grimstroke.info.rbo.admin.LiveStreamRbo;
import com.ouresports.grimstroke.info.rbo.admin.SeriesIdRbo;
import com.ouresports.grimstroke.info.service.LiveStreamService;
import com.ouresports.grimstroke.info.service.SeriesStreamService;
import com.ouresports.grimstroke.info.vo.admin.LiveStreamUrlVo;
import com.ouresports.grimstroke.lib.livestream.service.LivestreamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.ouresports.grimstroke.info.enums.LiveStreamType.*;

/**
 *
 * @author will
 * @date 2018/12/19
 */
@RestCrudController(value="AdminLiveStreamController")
@RequestMapping(value="/admin/live_streams", produces="application/json;charset=UTF-8")
@RestIndex
@RestShow
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
        if (rbo.getType() == Official) {
            baseService.createExternLiveStream(rbo.convertTo(), rbo.getPlatform(), rbo.getRoomId());
        } else {
            baseService.createOuresportsLiveStream(rbo.convertTo());
        }
        return render(ResultTemplate.createOk());
    }

    /**
     * 停止外部直播流
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping(value="/{id}/stop")
    public ResponseEntity stop(@PathVariable long id) throws Exception {
        LiveStream liveStream = baseService.find(id);
        if (liveStream.getType() == Anchor) {
            if (livestreamService.deleteLivestreamSync(liveStream.getId().toString())) {
                liveStream.setActive(false);
                baseService.updateById(liveStream);
            }
        }
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

    /**
     * 直播流绑定比赛
     * @param id
     * @param rbo
     * @return
     * @throws Exception
     */
    @PostMapping(value="/{id}/series")
    public ResponseEntity addSeries(@PathVariable long id,
                                    @Valid @RequestBody SeriesIdRbo rbo) throws Exception {
        seriesStreamService.addSeries(baseService.find(id), rbo.getSeriesId());
        return render(ResultTemplate.createOk());
    }

    /**
     * 直播流解除绑定比赛
     * @param id
     * @param rbo
     * @return
     * @throws Exception
     */
    @PostMapping(value="/{id}/remove_series")
    public ResponseEntity removeSeries(@PathVariable long id,
                                       @Valid @RequestBody SeriesIdRbo rbo) throws Exception {
        seriesStreamService.removeSeries(baseService.find(id), rbo.getSeriesId());
        return render(ResultTemplate.deleteOk());
    }
}
