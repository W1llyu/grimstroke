package com.ouresports.grimstroke.info.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.base.template.PaginationTemplate;
import com.ouresports.grimstroke.info.dto.TagDto;
import com.ouresports.grimstroke.info.entity.Tag;
import com.ouresports.grimstroke.info.service.TagService;
import com.ouresports.grimstroke.info.vo.api.ParentTagVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by will on 2019/3/15.
 */
@RestController
@RequestMapping(value="/api/tags", produces="application/json;charset=UTF-8")
public class TagController extends BaseController {
    @Resource
    private TagService tagService;

    @GetMapping(value="/parent_tags")
    public ResponseEntity index(@RequestParam(value="page", defaultValue="1") int currentPage,
                                @RequestParam(defaultValue="10") int per) throws Exception {
        Page<TagDto> page = new Page<>(currentPage, per);
        QueryWrapper<Tag> wrapper = new QueryWrapper<>(new Tag().setParentTagId(0L));
        IPage<TagDto> tagPages = tagService.getDtos(page, wrapper);
        for (TagDto tagDto: tagPages.getRecords()) {
            tagDto.setChildren(tagService.list(new QueryWrapper<>(new Tag().setParentTagId(tagDto.getId()))));
        }
        return render(new PaginationTemplate<>(tagPages, ParentTagVo.class));
    }
}
