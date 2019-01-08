package com.ouresports.grimstroke.im.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.base.annotation.AuthToken;
import com.ouresports.grimstroke.base.template.PaginationTemplate;
import com.ouresports.grimstroke.base.template.SingleTemplate;
import com.ouresports.grimstroke.im.dto.RoomMessageDto;
import com.ouresports.grimstroke.im.entity.MatchSeries;
import com.ouresports.grimstroke.im.entity.RoomMessage;
import com.ouresports.grimstroke.im.rbo.api.RoomMessageRbo;
import com.ouresports.grimstroke.im.service.ChatRoomBanService;
import com.ouresports.grimstroke.im.service.MatchSeriesService;
import com.ouresports.grimstroke.im.service.RoomMessageService;
import com.ouresports.grimstroke.im.vo.api.MetaVo;
import com.ouresports.grimstroke.im.vo.api.RoomInfoVo;
import com.ouresports.grimstroke.im.vo.api.RoomMessageVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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
    private ChatRoomBanService chatRoomBanService;

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
                .setChannel(roomMessageService.getRoomChannel(roomName))
                .setOpen(matchSeriesService.chatRoomOpen(matchSeries));
        return render(new SingleTemplate<>(roomInfoVo));
    }

    /**
     * 在聊天室发送消息
     * @param id
     * @param rbo
     * @return
     * @throws Exception
     */
    @AuthToken
    @PostMapping(value="/{id}/room_messages")
    public ResponseEntity sendRoomMessage(@PathVariable long id,
                                          @Valid @RequestBody RoomMessageRbo rbo) throws Exception {
        MatchSeries matchSeries = matchSeriesService.find(id);
        matchSeriesService.checkChatRoomOpen(matchSeries);
        chatRoomBanService.checkUserIsBan(getCurrentUser());
        roomMessageService.checkFrequency(getCurrentUser(), matchSeriesService.getRoomName(matchSeries));
        RoomMessageDto dto = roomMessageService.createMessageAndNotify(getCurrentUser(), matchSeriesService.getRoomName(matchSeries), rbo.getContent());
        return render(new SingleTemplate<>(dto, RoomMessageVo.class));
    }

    /**
     * 查询聊天室消息
     * @param id
     * @param currentPage
     * @param per
     * @param lastId
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{id}/room_messages")
    public ResponseEntity getRoomMessages(@PathVariable long id,
                                          @RequestParam(value="page", defaultValue="1") int currentPage,
                                          @RequestParam(defaultValue="10") int per,
                                          @RequestParam(value="last_id", required=false) Long lastId) throws Exception {
        Page<RoomMessage> page = new Page<>(currentPage, per);
        QueryWrapper<RoomMessage> wrapper = new QueryWrapper<>(new RoomMessage()
                .setRoomName(matchSeriesService.getRoomName(matchSeriesService.find(id))))
                .orderByDesc("created_at");
        if (lastId != null) {
            wrapper.lt("`id`", lastId);
        }
        IPage<RoomMessageDto> dtos = roomMessageService.getRoomMessageDtos(page, wrapper);
        MetaVo metaVo = MetaVo.builder()
                .currentPage(dtos.getCurrent())
                .totalCount(dtos.getTotal())
                .per(dtos.getSize()).build();
        if (dtos.getRecords().size() > 0) {
            metaVo.setLastId(dtos.getRecords().get(dtos.getRecords().size() - 1).getId());
        }
        return render(new PaginationTemplate<>(dtos.getRecords(), RoomMessageVo.class, metaVo));
    }
}
