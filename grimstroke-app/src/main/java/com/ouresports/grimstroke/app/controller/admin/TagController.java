package com.ouresports.grimstroke.app.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.app.base.template.PaginationTemplate;
import com.ouresports.grimstroke.app.base.template.ResultTemplate;
import com.ouresports.grimstroke.app.base.template.SingleTemplate;
import com.ouresports.grimstroke.app.rbo.admin.TagRbo;
import com.ouresports.grimstroke.core.entity.Tag;
import com.ouresports.grimstroke.core.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *
 * @author will
 * @date 2018/12/6
 */
@RestController(value="AdminTagController")
@RequestMapping(value="/admin/tags", produces="application/json;charset=UTF-8")
public class TagController extends BaseController {
    @Resource
    private TagService tagService;

    /**
     * 列表
     * @param currentPage
     * @param per
     * @param parsenTagId
     * @return
     * @throws Exception
     */
    @GetMapping(value="")
    public ResponseEntity index(@RequestParam(value="page", defaultValue="1") int currentPage,
                                @RequestParam(defaultValue="10") int per,
                                @RequestParam(value="parent_tag_id", required=false) Long parsenTagId) throws Exception {
        Page<Tag> page = new Page<>(currentPage, per);
        Tag tag = new Tag().setParentTagId(parsenTagId);
        return render(new PaginationTemplate<>(tagService.list(page, tag)));
    }

    /**
     * 详情
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{id}")
    public ResponseEntity show(@PathVariable long id) throws Exception {
        Tag tag = tagService.find(id);
        return render(new SingleTemplate<>(tag));
    }

    /**
     * 创建
     * @param rbo
     * @return
     * @throws Exception
     */
    @PostMapping(value="")
    public ResponseEntity create(@Valid @RequestBody TagRbo rbo) throws Exception {
        tagService.save(rbo.convertTo());
        return render(ResultTemplate.createOk());
    }

    /**
     * 更新
     * @param id
     * @param rbo
     * @return
     * @throws Exception
     */
    @PatchMapping(value="/{id}")
    public ResponseEntity update(@PathVariable long id,
                                 @RequestBody TagRbo rbo) throws Exception {
        Tag tag = rbo.convertTo();
        tag.setId(id);
        tagService.updateById(tag);
        return render(ResultTemplate.updateOk());
    }

    /**
     * 删除
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping(value="/{id}")
    public ResponseEntity delete(@PathVariable long id) throws Exception {
        tagService.removeById(id);
        return render(ResultTemplate.deleteOk());
    }
}
