package com.ouresports.grimstroke.info.controller.api;

import com.google.common.collect.Lists;
import com.ouresports.grimstroke.base.template.SingleTemplate;
import com.ouresports.grimstroke.info.dto.LiveStreamDto;
import com.ouresports.grimstroke.info.dto.SeriesLiveStreamCountDto;
import com.ouresports.grimstroke.info.service.SeriesStreamService;
import com.ouresports.grimstroke.info.vo.api.LiveStreamVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author will
 * @date 2018/12/20
 */
@RestController("InfoSeriesController")
@RequestMapping(value="/api/series", produces="application/json;charset=UTF-8")
public class SeriesController extends BaseController {
    @Resource
    private SeriesStreamService seriesStreamService;

    /**
     * 获得系列赛的直播流
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{id}/live_streams")
    public ResponseEntity getLiveStreams(@PathVariable long id) throws Exception {
        List<LiveStreamDto> dtos = seriesStreamService.getLiveStreamDtosBySeries(id);
        return render(new SingleTemplate<>(dtos, LiveStreamVo.class));
    }

    /**
     * 获取系列赛列表的直播流
     * @param ids
     * @return
     * @throws Exception
     */
    @GetMapping(value="/live_streams")
    public ResponseEntity getLiveStreams(@RequestParam String ids) throws Exception {
        List<Long> idList = Lists.newArrayList();
        for (String id: ids.split(",")) {
            idList.add(Long.parseLong(id));
        }
        List<SeriesLiveStreamCountDto> dtos = seriesStreamService.getSeriesLiveStreamCount(idList);
        return render(new SingleTemplate<>(dtos));
    }
}
