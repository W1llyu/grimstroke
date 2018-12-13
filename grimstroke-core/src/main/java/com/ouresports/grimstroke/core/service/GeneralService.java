package com.ouresports.grimstroke.core.service;

import com.ouresports.grimstroke.core.dto.CommentDto;
import com.ouresports.grimstroke.core.dto.LikeDto;
import com.ouresports.grimstroke.core.dto.NormalUserDto;
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
                    return new LikeDto().convertFor(likeService.find(id));
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
