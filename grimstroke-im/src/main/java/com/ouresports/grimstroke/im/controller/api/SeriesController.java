package com.ouresports.grimstroke.im.controller.api;

import com.ouresports.grimstroke.base.template.SingleTemplate;
import com.ouresports.grimstroke.im.entity.MatchSeries;
import com.ouresports.grimstroke.im.service.ChatRoomBanService;
import com.ouresports.grimstroke.im.service.ChatRoomService;
import com.ouresports.grimstroke.im.service.MatchSeriesService;
import com.ouresports.grimstroke.im.service.RoomMessageService;
import com.ouresports.grimstroke.im.vo.api.RoomInfoVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *
 * @author will
 * @date 2018/12/21
 */
@RestController("ImSeriesController")
@RequestMapping(value="/api/series", produces="application/json;charset=UTF-8")
public class SeriesController extends BaseController {
    @Resource
    private MatchSeriesService matchSeriesService;
    @Resource
    private RoomMessageService roomMessageService;
    @Resource
    private ChatRoomService chatRoomService;

    /**
     * 获取赛事聊天室信息
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{id}/room")
    public ResponseEntity getRoomInfo(@PathVariable long id) throws Exception {
        MatchSeries matchSeries = matchSeriesService.find(id);
        String roomName = matchSeriesService.getRoomName(matchSeries);
        RoomInfoVo roomInfoVo = new RoomInfoVo()
                .setName(roomName)
                .setChannel(chatRoomService.getRoomChannel(roomName))
                .setOpen(matchSeriesService.chatRoomOpen(matchSeries));
        return render(new SingleTemplate<>(roomInfoVo));
    }
}
