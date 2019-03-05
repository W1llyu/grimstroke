package com.ouresports.grimstroke.info.mapper;

import com.ouresports.grimstroke.base.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.info.dto.CommentDto;
import com.ouresports.grimstroke.info.dto.SubCommentDto;
import com.ouresports.grimstroke.info.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author will
 * @date 2018/11/27
 */
@Component
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 分页查询评论
     * @param page
     * @param wrapper
     * @return
     */
    List<CommentDto> selectCommentDtos(IPage<CommentDto> page,
                                       @Param("ew") Wrapper<Comment> wrapper,
                                       @Param("user_id") long userId);

    /**
     * 分页查询子评论
     * @param page
     * @param wrapper
     * @param userId
     * @return
     */
    List<SubCommentDto> selectSubCommentDtos(IPage<SubCommentDto> page,
                                             @Param("ew") Wrapper<Comment> wrapper,
                                             @Param("user_id") long userId);

    /**
     * 获取评论的最近几条子评论
     * @return
     */
    List<SubCommentDto> selectSubCommentLimit(@Param("id") long id,
                                              @Param("user_id") long userId);
}
