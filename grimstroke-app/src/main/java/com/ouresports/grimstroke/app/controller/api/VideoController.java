package com.ouresports.grimstroke.app.controller.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.app.base.template.PaginationTemplate;
import com.ouresports.grimstroke.app.base.template.ResultTemplate;
import com.ouresports.grimstroke.app.base.template.SingleTemplate;
import com.ouresports.grimstroke.app.rbo.api.CommentRbo;
import com.ouresports.grimstroke.app.vo.api.CommentVo;
import com.ouresports.grimstroke.core.dto.CommentDto;
import com.ouresports.grimstroke.core.dto.VideoDto;
import com.ouresports.grimstroke.core.entity.Video;
import com.ouresports.grimstroke.core.service.CommentService;
import com.ouresports.grimstroke.core.service.LikeService;
import com.ouresports.grimstroke.core.service.VideoService;
import com.ouresports.grimstroke.lib.aliyun.entity.VodPlayAuthResponse;
import com.ouresports.grimstroke.lib.aliyun.service.AliyunVodService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *
 * @author will
 * @date 2018/12/5
 */
@RestController
@RequestMapping(value="/api/videos", produces="application/json;charset=UTF-8")
public class VideoController extends BaseController {
    @Resource
    private AliyunVodService aliyunVodService;
    @Resource
    private VideoService videoService;
    @Resource
    private CommentService commentService;
    @Resource
    private LikeService likeService;

    /**
     * 获取视频播放凭证
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{id}/play_auth")
    public ResponseEntity getPlayAuth(@PathVariable long id) throws Exception {
        VodPlayAuthResponse vodPlayAuth = aliyunVodService.getVideoPlayAuth(loadVideoById(id).getVodId());
        return render(new SingleTemplate<>(vodPlayAuth));
    }

    /**
     * 获取视频列表
     * @param currentPage
     * @param per
     * @param gameId
     * @return
     * @throws Exception
     */
    @GetMapping(value="")
    public ResponseEntity index(@RequestParam(value="page", defaultValue="1") int currentPage,
                                @RequestParam(defaultValue="10") int per,
                                @RequestParam(value="game_id", required=false) Integer gameId) throws Exception {
        Page<VideoDto> page = new Page<>(currentPage, per);
        IPage<VideoDto> videoDtos = videoService.getVideoDtos(page, gameId);
        return render(new PaginationTemplate<>(videoDtos));
    }

    /**
     * 视频详情
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{id}")
    public ResponseEntity show(@PathVariable long id) throws Exception {
        VideoDto dto = videoService.getVideoDto(id);
        return render(new SingleTemplate<>(dto));
    }

    /**
     * 获得视频的评论
     * @param id
     * @param currentPage
     * @param per
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{id}/comments")
    public ResponseEntity comments(@PathVariable long id,
                                   @RequestParam(value="page", defaultValue="1") int currentPage,
                                   @RequestParam(defaultValue="10") int per) throws Exception {
        authenticateUser();
        Video video = loadVideoById(id);
        Page<CommentDto> page = new Page<>(currentPage, per);
        IPage<CommentDto> commentDtoIPage = commentService.getCommentDtoPage(page, video, currentUser);
        return render(new PaginationTemplate<>(commentDtoIPage, CommentVo.class));
    }

    /**
     * 评论视频
     * @param id
     * @param comment
     * @return
     * @throws Exception
     */
    @PostMapping(value="/{id}/comments")
    public ResponseEntity addComment(@PathVariable long id,
                                     @Valid @RequestBody CommentRbo comment) throws Exception {
        authenticateUserForce();
        commentService.addComment(currentUser, loadVideoById(id), comment.getContent());
        return render(ResultTemplate.createOk());
    }

    /**
     * 用户点赞一条视频
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping(value="/{id}/like")
    public ResponseEntity likeComment(@PathVariable long id) throws Exception {
        authenticateUserForce();
        likeService.addLike(currentUser, loadVideoById(id));
        return render(ResultTemplate.createOk());
    }

    /**
     * 取消点赞一条视频
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping(value="/{id}/remove_like")
    public ResponseEntity removeLikeComment(@PathVariable long id) throws Exception {
        authenticateUserForce();
        likeService.removeLike(currentUser, loadVideoById(id));
        return render(ResultTemplate.deleteOk());
    }

    private Video loadVideoById(long id) throws NotFoundException {
        return videoService.find(id);
    }
}
