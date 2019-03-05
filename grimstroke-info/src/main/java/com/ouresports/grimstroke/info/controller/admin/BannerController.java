package com.ouresports.grimstroke.info.controller.admin;

import com.ouresports.grimstroke.base.annotation.*;
import com.ouresports.grimstroke.info.rbo.admin.BannerRbo;
import com.ouresports.grimstroke.info.entity.Banner;
import com.ouresports.grimstroke.info.service.BannerService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author will
 * @date 2018/12/6
 */
@RestCrudController(value="AdminBannerController")
@RequestMapping(value="/admin/banners", produces="application/json;charset=UTF-8")
@RestIndex(dto=true)
@RestShow(dto=true)
@RestCreate(rboClass=BannerRbo.class)
@RestUpdate(rboClass=BannerRbo.class, valid=false)
@RestDelete
public class BannerController extends BaseController<Banner, BannerService> {
}
