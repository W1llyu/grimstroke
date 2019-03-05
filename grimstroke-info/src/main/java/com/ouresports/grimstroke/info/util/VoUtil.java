package com.ouresports.grimstroke.info.util;

import com.ouresports.grimstroke.base.util.BeanUtil;
import com.ouresports.grimstroke.info.dto.CommentDto;
import com.ouresports.grimstroke.info.dto.NewsDto;
import com.ouresports.grimstroke.info.entity.Comment;
import com.ouresports.grimstroke.info.entity.News;
import com.ouresports.grimstroke.info.vo.api.target.CommentDtoTargetVo;
import com.ouresports.grimstroke.info.vo.api.target.CommentTargetVo;
import com.ouresports.grimstroke.info.vo.api.target.NewsTargetVo;

/**
 *
 * @author will
 * @date 2018/12/17
 */
public class VoUtil {
    public static Object generateTargetVo(Object target) {
        Object targetVo;
        if (target == null) {
            return null;
        }
        if (target.getClass() == CommentDto.class) {
            targetVo = new CommentDtoTargetVo();
        } else if (target.getClass() == Comment.class) {
            targetVo = new CommentTargetVo();
        } else if (target.getClass() == News.class || target.getClass() == NewsDto.class) {
            targetVo = new NewsTargetVo();
        } else {
            return target;
        }
        BeanUtil.copyProperties(target, targetVo);
        return targetVo;
    }
}
