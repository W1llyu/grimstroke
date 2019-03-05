package com.ouresports.grimstroke.info.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.base.template.PaginationTemplate;
import com.ouresports.grimstroke.info.vo.api.BannerVo;
import com.ouresports.grimstroke.info.dto.BannerDto;
import com.ouresports.grimstroke.info.entity.Banner;
import com.ouresports.grimstroke.info.service.BannerService;
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
        Page<BannerDto> page = new Page<>(currentPage, per);
        QueryWrapper<Banner> banner = new QueryWrapper<>(new Banner().setEnabled(true))
                .orderByDesc("`banners`.`priority`");
        IPage<BannerDto> banners = bannerService.getDtos(page, banner);
        return render(new PaginationTemplate<>(banners, BannerVo.class));
    }
}
