package com.ouresports.grimstroke.app.controller.admin;

import com.ouresports.grimstroke.app.base.annotation.*;
import com.ouresports.grimstroke.app.rbo.admin.BannerRbo;
import com.ouresports.grimstroke.core.entity.Banner;
import com.ouresports.grimstroke.core.service.BannerService;
import org.springframework.web.bind.annotation.*;

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
