package com.ouresports.grimstroke.im.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.base.controller.AbstractController;
import com.ouresports.grimstroke.base.service.UserService;
import com.ouresports.grimstroke.base.template.PaginationTemplate;
import com.ouresports.grimstroke.base.template.ResultTemplate;
import com.ouresports.grimstroke.im.dto.ChatRoomBanDto;
import com.ouresports.grimstroke.im.entity.ChatRoomBan;
import com.ouresports.grimstroke.im.rbo.admin.BanTimeRbo;
import com.ouresports.grimstroke.im.service.ChatRoomBanService;
import com.ouresports.grimstroke.im.service.RoomMessageService;
import com.ouresports.grimstroke.im.vo.admin.BanUserVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 *
 * @author will
 * @date 2018/12/22
 */
@RestController("AdminChatRoomController")
@RequestMapping(value="/admin/chat_rooms", produces="application/json;charset=UTF-8")
public class ChatRoomController extends AbstractController {
    @Resource
    private RoomMessageService roomMessageService;
    @Resource
    private ChatRoomBanService chatRoomBanService;
    @Resource
    private UserService userService;

    /**
     * 删除一条消息
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping(value="/messages/{id}")
    public ResponseEntity deleteMessage(@PathVariable long id) throws Exception {
        roomMessageService.deleteMessageAndNotify(roomMessageService.find(id));
        return render(ResultTemplate.deleteOk());
    }

    /**
     * 获得所有被禁言的用户
     * @param currentPage
     * @param per
     * @return
     * @throws Exception
     */
    @GetMapping(value="/ban_users")
    public ResponseEntity getUsers(@RequestParam(value="page", defaultValue="1") int currentPage,
                                   @RequestParam(defaultValue="10") int per) throws Exception {
        IPage<ChatRoomBan> page = new Page<>(currentPage, per);
        QueryWrapper<ChatRoomBan> wrapper = new QueryWrapper<ChatRoomBan>().gt("ban_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date()));
        IPage<ChatRoomBan> chatRoomBanIPage = chatRoomBanService.list(page, wrapper);
        List<ChatRoomBanDto> dtos = chatRoomBanIPage.getRecords().stream().map(x -> (ChatRoomBanDto) new ChatRoomBanDto().convertFor(x)).collect(Collectors.toList());
        userService.includeUsers(dtos);
        return render(new PaginationTemplate<>(new Page<ChatRoomBanDto>(currentPage, per, chatRoomBanIPage.getTotal()).setRecords(dtos), BanUserVo.class));
    }

    /**
     * 用户禁言
     * @param id
     * @param rbo
     * @return
     * @throws Exception
     */
    @PostMapping(value="/users/{id}/ban")
    public ResponseEntity setUserBan(@PathVariable long id,
                                     @Valid @RequestBody BanTimeRbo rbo) throws Exception {
        chatRoomBanService.setUserBanTime(userService.find(id), rbo.getBanTime());
        return render(ResultTemplate.createOk());
    }

    /**
     * 用户解除禁言
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping(value="/users/{id}/remove_ban")
    public ResponseEntity removeUserBan(@PathVariable long id) throws Exception {
        chatRoomBanService.remove(new QueryWrapper<ChatRoomBan>().eq("user_id", id));
        return render(ResultTemplate.deleteOk());
    }
}
