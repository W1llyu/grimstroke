package com.ouresports.grimstroke.im.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.base.annotation.AuthToken;
import com.ouresports.grimstroke.base.template.PaginationTemplate;
import com.ouresports.grimstroke.base.template.ResultTemplate;
import com.ouresports.grimstroke.base.template.SingleTemplate;
import com.ouresports.grimstroke.im.dto.RoomMessageDto;
import com.ouresports.grimstroke.im.entity.MatchSeries;
import com.ouresports.grimstroke.im.entity.RoomMessage;
import com.ouresports.grimstroke.im.rbo.api.RoomMessageRbo;
import com.ouresports.grimstroke.im.service.ChatRoomBanService;
import com.ouresports.grimstroke.im.service.MatchSeriesService;
import com.ouresports.grimstroke.im.service.RoomMessageService;
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
        RoomMessageDto dto = roomMessageService.createMessageAndNotify(getCurrentUser(), matchSeriesService.getRoomName(matchSeries), rbo.getContent());
        return render(new SingleTemplate<>(dto, RoomMessageVo.class));
    }

    /**
     * 查询聊天室消息
     * @param id
     * @param currentPage
     * @param per
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{id}/room_messages")
    public ResponseEntity getRoomMessages(@PathVariable long id,
                                          @RequestParam(value="page", defaultValue="1") int currentPage,
                                          @RequestParam(defaultValue="10") int per) throws Exception {
        Page<RoomMessage> page = new Page<>(currentPage, per);
        QueryWrapper<RoomMessage> wrapper = new QueryWrapper<>(new RoomMessage()
                .setRoomName(matchSeriesService.getRoomName(matchSeriesService.find(id))))
                .orderByDesc("created_at");
        IPage<RoomMessageDto> dtos = roomMessageService.getRoomMessageDtos(page, wrapper);
        return render(new PaginationTemplate<>(dtos, RoomMessageVo.class));
    }
}
