package com.ouresports.grimstroke.app.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.core.entity.Banner;
import com.ouresports.grimstroke.core.service.BannerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 * @author will
 * @date 2018/12/6
 */
@RestController(value = "AdminBannerController")
@RequestMapping(value="/admin/banners", produces="application/json;charset=UTF-8")
public class BannerController extends BaseController {
    @Resource
    private BannerService bannerService;

    @GetMapping(value="")
    public ResponseEntity index(@RequestParam(value="page", defaultValue="1") int currentPage,
                                @RequestParam(defaultValue="10") int per) throws Exception {
        Page<Banner> page = new Page<>(currentPage, per);
        return null;
    }
}
