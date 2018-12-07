package com.ouresports.grimstroke.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.core.base.service.Service;
import com.ouresports.grimstroke.core.concern.Commentable;
import com.ouresports.grimstroke.core.dto.CommentDto;
import com.ouresports.grimstroke.core.dto.SubCommentDto;
import com.ouresports.grimstroke.core.entity.Comment;
import com.ouresports.grimstroke.core.entity.User;
import com.ouresports.grimstroke.core.exception.ServiceException;
import org.apache.ibatis.javassist.NotFoundException;

/**
 *
 * @author will
 * @date 2018/11/27
 */
public interface CommentService extends Service<Comment> {
    /**
     * 添加一条评论
     * @param user
     * @param commentable
     * @param content
     */
    Comment addComment(User user, Commentable commentable, String content) throws ServiceException;

    /**
     * 获得一条评论详情
     * @param id
     * @param user
     * @return
     */
    CommentDto getCommentDto(long id, User user) throws NotFoundException;

    /**
     * 分页获得评论
     * @param page
     * @param commentable
     * @param user
     * @return
     */
    IPage<CommentDto> getCommentDtos(IPage<CommentDto> page, Commentable commentable, User user);

    /**
     * 分页获得评论的回复
     * @param page
     * @param comment
     * @param user
     * @return
     */
    IPage<SubCommentDto> getSubCommentDtos(IPage<SubCommentDto> page, Comment comment, User user);
}
