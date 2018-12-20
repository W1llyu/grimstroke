package com.ouresports.grimstroke.info.service;

import com.ouresports.grimstroke.base.service.UserService;
import com.ouresports.grimstroke.info.dto.CommentDto;
import com.ouresports.grimstroke.info.dto.LikeDto;
import com.ouresports.grimstroke.info.dto.NormalUserDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @author will
 * @date 2018/12/13
 */
@Service
public class GeneralService {
    @Resource
    private NewsService newsService;
    @Resource
    private VideoService videoService;
    @Resource
    private CommentService commentService;
    @Resource
    private LikeService likeService;
    @Resource
    private UserService userService;

    public Object getPolymorphicDto(String type, long id) {
        try {
            switch (type) {
                case "News":
                    return newsService.getDto(id);
                case "Videos":
                    return videoService.getDto(id);
                case "Comment":
                    CommentDto commentDto = commentService.getDto(id);
                    commentDto.setUser((NormalUserDto) new NormalUserDto().convertFor(userService.find(commentDto.getUserId())));
                    return commentDto;
                case "Like":
                    LikeDto likeDto = (LikeDto) new LikeDto().convertFor(likeService.find(id));
                    likeDto.setUser((NormalUserDto) new NormalUserDto().convertFor(userService.find(likeDto.getUserId())));
                    return likeDto;
                default:
                    return null;
            }
        } catch (Exception ignored) {
            return null;
        }
    }

    public Object getPolymorphicEntity(String type, long id) {
        try {
            switch (type) {
                case "News":
                    return newsService.find(id);
                case "Videos":
                    return videoService.find(id);
                case "Comment":
                    return commentService.find(id);
                case "Like":
                    return likeService.find(id);
                default:
                    return null;
            }
        } catch (Exception ignored) {
            return null;
        }
    }
}
