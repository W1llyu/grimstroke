package com.ouresports.grimstroke.info.controller.admin;

import com.ouresports.grimstroke.base.annotation.*;
import com.ouresports.grimstroke.info.rbo.admin.TagRbo;
import com.ouresports.grimstroke.info.entity.Tag;
import com.ouresports.grimstroke.info.service.TagService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author will
 * @date 2018/12/6
 */
@RestCrudController(value="AdminTagController")
@RequestMapping(value="/admin/tags", produces="application/json;charset=UTF-8")
@RestIndex(dto=true)
@RestShow(dto=true)
@RestCreate(rboClass=TagRbo.class)
@RestUpdate(rboClass=TagRbo.class, valid=false)
@RestDelete
public class TagController extends BaseController<Tag, TagService> {
}
