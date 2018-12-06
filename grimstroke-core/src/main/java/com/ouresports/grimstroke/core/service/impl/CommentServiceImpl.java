package com.ouresports.grimstroke.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.ouresports.grimstroke.core.base.service.BaseServiceImpl;
import com.ouresports.grimstroke.core.concern.Commentable;
import com.ouresports.grimstroke.core.dto.CommentDto;
import com.ouresports.grimstroke.core.dto.NormalUserDto;
import com.ouresports.grimstroke.core.dto.SubCommentDto;
import com.ouresports.grimstroke.core.entity.Comment;
import com.ouresports.grimstroke.core.entity.User;
import com.ouresports.grimstroke.core.exception.ServiceException;
import com.ouresports.grimstroke.core.mapper.CommentMapper;
import com.ouresports.grimstroke.core.service.CommentService;
import com.ouresports.grimstroke.core.service.UserService;
import com.ouresports.grimstroke.core.util.CollectionUtil;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.ouresports.grimstroke.core.enums.ServiceError.CANNOT_COMMENT_SELF;

/**
 *
 * @author will
 * @date 2018/11/27
 */
@Service
public class CommentServiceImpl extends BaseServiceImpl<CommentMapper, Comment> implements CommentService {
    @Resource
    private UserService userService;

    @Override
    public Comment addComment(User user, Commentable commentable, String content) throws ServiceException {
        String rootType;
        Long rootId, rootCommentId = null, parentCommentId = null;
        // 回复评论时
        if (commentable.getClass() == Comment.class) {
            Comment parentComment = (Comment) commentable;
            if (user.getId().equals(parentComment.getUserId())) {
                throw new ServiceException(CANNOT_COMMENT_SELF);
            }
            // 父评论也是对评论的回复
            if (parentComment.getRootCommentId() != null) {
                rootCommentId = parentComment.getRootCommentId();
            } else {
                // 父评论是对信息流的评论
                rootCommentId = parentComment.getId();
            }
            rootType = parentComment.getRootType();
            rootId = parentComment.getRootId();
            parentCommentId = parentComment.getId();
        } else {
            // 直接对信息流评论
            rootType = commentable.getCommentableType();
            rootId = commentable.getId();
        }
        Comment comment = new Comment()
                .setUserId(user.getId())
                .setRootType(rootType)
                .setRootId(rootId)
                .setRootCommentId(rootCommentId)
                .setParentCommentId(parentCommentId)
                .setContent(content);
        save(comment);
        return comment;
    }

    @Override
    public CommentDto getCommentDto(long id, User user) throws NotFoundException {
        QueryWrapper<Comment> wrapper = new QueryWrapper<Comment>()
                .eq("`comments`.`id`", id)
                .groupBy("`comments`.`id`")
                .last("LIMIT 1");
        List<CommentDto> list = baseMapper.selectCommentDtos(null, wrapper, user == null ? 0 : user.getId());
        setUserForCommentDto(list);
        return CollectionUtil.getFirstElement(list);
    }

    @Override
    public IPage<CommentDto> getCommentDtos(IPage<CommentDto> page, Wrapper<Comment> wrapper, User user) {
        List<CommentDto> commentDtoList = baseMapper.selectCommentDtos(page, wrapper, user == null ? 0 : user.getId());
        page.setRecords(commentDtoList);
        return page;
    }

    @Override
    public IPage<CommentDto> getCommentDtos(IPage<CommentDto> page, Commentable commentable, User user) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<Comment>()
                .eq("`comments`.`root_type`", commentable.getCommentableType())
                .eq("`comments`.`root_id`", commentable.getId())
                .eq("`comments`.`enabled`", true)
                .isNull("`comments`.`root_comment_id`")
                .groupBy("`comments`.`id`")
                .orderByDesc("`comments`.`created_at`");
        page.setRecords(baseMapper.selectCommentDtos(page, wrapper, user == null ? 0 : user.getId()));
        return page;
    }

    @Override
    public IPage<SubCommentDto> getSubCommentDtos(IPage<SubCommentDto> page, Comment comment, User user) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<Comment>()
                .eq("`comments`.`root_comment_id`", comment.getId())
                .groupBy("`comments`.`id`")
                .orderByDesc("`comments`.`created_at`");
        List<SubCommentDto> subCommentDtoS = baseMapper.selectSubCommentDtos(page, wrapper, user == null ? 0 : user.getId());
        setUserForSubCommentDto(subCommentDtoS);
        page.setRecords(subCommentDtoS);
        return page;
    }

    private void setUserForCommentDto(List<CommentDto> list) {
        Set<Long> userIds = getUserIdsFromCommentDtos(list);
        if (userIds.isEmpty()) {
            return;
        }
        List<User> users = (List<User>) userService.listByIds(userIds);
        Map<Long, User> userMap = Maps.newHashMap();
        for (User user: users) {
            userMap.put(user.getId(), user);
        }
        for (CommentDto commentDto: list) {
            commentDto.setUser((NormalUserDto) new NormalUserDto().convertFor(userMap.get(commentDto.getUserId())));
            for (SubCommentDto subCommentDto: commentDto.getSubComments()) {
                subCommentDto.setUser((NormalUserDto) new NormalUserDto().convertFor(userMap.get(subCommentDto.getUserId())));
                subCommentDto.setTargetUser((NormalUserDto) new NormalUserDto().convertFor(userMap.get(subCommentDto.getTargetUserId())));
            }
        }
    }

    private void setUserForSubCommentDto(List<SubCommentDto> list) {
        Set<Long> userIds = getUserIdsFromSubCommentDtos(list);
        if (userIds.isEmpty()) {
            return;
        }
        List<User> users = (List<User>) userService.listByIds(userIds);
        Map<Long, User> userMap = Maps.newHashMap();
        for (User user: users) {
            userMap.put(user.getId(), user);
        }
        for (SubCommentDto subCommentDto: list) {
            subCommentDto.setUser((NormalUserDto) new NormalUserDto().convertFor(userMap.get(subCommentDto.getUserId())));
            subCommentDto.setTargetUser((NormalUserDto) new NormalUserDto().convertFor(userMap.get(subCommentDto.getTargetUserId())));
        }
    }

    private Set<Long> getUserIdsFromCommentDtos(List<CommentDto> list) {
        Set<Long> userIds = Sets.newHashSet();
        try {
            userIds.addAll(CollectionUtil.getElementAttrsOfList(list, "userId"));
            for (CommentDto commentDto : list) {
                userIds.addAll(getUserIdsFromSubCommentDtos(commentDto.getSubComments()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userIds;
    }

    private Set<Long> getUserIdsFromSubCommentDtos(List<SubCommentDto> list) {
        Set<Long> userIds = Sets.newHashSet();
        try {
            userIds.addAll(CollectionUtil.getElementAttrsOfList(list, "userId"));
            userIds.addAll(CollectionUtil.getElementAttrsOfList(list, "targetUserId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userIds;
    }
}
