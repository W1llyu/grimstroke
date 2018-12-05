package com.ouresports.grimstroke.app.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.app.base.template.PaginationTemplate;
import com.ouresports.grimstroke.app.vo.api.BannerVo;
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
 * @date 2018/12/5
 */
@RestController
@RequestMapping(value="/api/banners", produces="application/json;charset=UTF-8")
public class BannerController extends BaseController {
    @Resource
    private BannerService bannerService;

    /**
     * banner列表
     * @param currentPage
     * @param per
     * @return
     * @throws Exception
     */
    @GetMapping(value="")
    public ResponseEntity index(@RequestParam(value="page", defaultValue="1") int currentPage,
                                @RequestParam(defaultValue="10") int per) throws Exception {
        Page<Banner> page = new Page<>(currentPage, per);
        IPage<Banner> banners = bannerService.list(page, new QueryWrapper<Banner>()
                .eq("enabled", true)
                .orderByDesc("priority")
                .orderByDesc("created_at"));
        return render(new PaginationTemplate<>(banners, BannerVo.class));
    }
}
