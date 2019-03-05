package com.ouresports.grimstroke.im.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.base.annotation.AuthToken;
import com.ouresports.grimstroke.base.entity.User;
import com.ouresports.grimstroke.base.service.UserService;
import com.ouresports.grimstroke.base.template.PaginationTemplate;
import com.ouresports.grimstroke.base.template.ResultTemplate;
import com.ouresports.grimstroke.base.template.SingleTemplate;
import com.ouresports.grimstroke.im.dto.RoomMessageDto;
import com.ouresports.grimstroke.im.entity.RoomMessage;
import com.ouresports.grimstroke.im.rbo.api.RoomMessageRbo;
import com.ouresports.grimstroke.im.service.ChatRoomBanService;
import com.ouresports.grimstroke.im.service.ChatRoomService;
import com.ouresports.grimstroke.im.service.RoomMessageService;
import com.ouresports.grimstroke.im.vo.api.MetaVo;
import com.ouresports.grimstroke.im.vo.api.RoomMessageVo;
import com.ouresports.grimstroke.im.vo.api.RoomRewardVo;
import com.ouresports.grimstroke.lib.zeus.entity.RewardRbo;
import com.ouresports.grimstroke.lib.zeus.service.ZeusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *
 * @author will
 * @date 2019/1/10
 */
@RestController
@RequestMapping(value="/api/chat_rooms", produces="application/json;charset=UTF-8")
public class ChatRoomController extends BaseController {
    @Resource
    private RoomMessageService roomMessageService;
    @Resource
    private ChatRoomBanService chatRoomBanService;
    @Resource
    private ChatRoomService chatRoomService;
    @Resource
    private UserService userService;
    @Resource
    private ZeusService zeusService;

    /**
     * 在聊天室发送消息
     * @param roomName
     * @param rbo
     * @return
     * @throws Exception
     */
    @AuthToken
    @PostMapping(value="/{roomName}/messages")
    public ResponseEntity sendRoomMessage(@PathVariable String roomName,
                                          @Valid @RequestBody RoomMessageRbo rbo) throws Exception {
        chatRoomBanService.checkUserIsBan(getCurrentUser());
        roomMessageService.checkFrequency(getCurrentUser(), roomName);
        RoomMessageDto dto = roomMessageService.createMessageAndNotify(getCurrentUser(), roomName, rbo.getContent());
        return render(new SingleTemplate<>(dto, RoomMessageVo.class));
    }

    /**
     * 在房间打赏
     * @param roomName
     * @param rbo
     * @return
     * @throws Exception
     */
    @AuthToken
    @PostMapping(value="/{roomName}/rewards")
    public ResponseEntity createReward(@PathVariable String roomName,
                                       @Valid @RequestBody RewardRbo rbo) throws Exception {
        User receiver = rbo.getReward().getReceiverId() == 1 ? getOfficialUser() : userService.find(rbo.getReward().getReceiverId());
        zeusService.createReward(getCurrentUser().getToken(), rbo);
        chatRoomService.rewardNotify(roomName, new RoomRewardVo()
                .setOperator(getCurrentUser())
                .setReceiver(receiver)
                .setAmount(rbo.getReward().getAmount())
                .setType(rbo.getReward().getType()));
        return render(ResultTemplate.createOk());
    }

    /**
     * 获取房间消息
     * @param roomName
     * @param currentPage
     * @param per
     * @param lastId
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{roomName}/messages")
    public ResponseEntity getRoomMessages(@PathVariable String roomName,
                                          @RequestParam(value="page", defaultValue="1") int currentPage,
                                          @RequestParam(defaultValue="10") int per,
                                          @RequestParam(value="last_id", required=false) Long lastId) throws Exception {
        Page<RoomMessage> page = new Page<>(currentPage, per);
        assert(roomName != null);
        QueryWrapper<RoomMessage> wrapper = new QueryWrapper<>(new RoomMessage()
                .setRoomName(roomName))
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

    private User getOfficialUser() {
        User user = new User();
        user.setId(1L);
        user.setName("电竞大师");
        return user;
    }
}
