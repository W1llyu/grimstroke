package com.ouresports.grimstroke.app.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.app.base.template.PaginationTemplate;
import com.ouresports.grimstroke.app.base.template.ResultTemplate;
import com.ouresports.grimstroke.app.base.template.SingleTemplate;
import com.ouresports.grimstroke.app.rbo.admin.BannerRbo;
import com.ouresports.grimstroke.core.dto.BannerDto;
import com.ouresports.grimstroke.core.entity.Banner;
import com.ouresports.grimstroke.core.service.BannerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *
 * @author will
 * @date 2018/12/6
 */
@RestController(value="AdminBannerController")
@RequestMapping(value="/admin/banners", produces="application/json;charset=UTF-8")
public class BannerController extends BaseController {
    @Resource
    private BannerService bannerService;

    /**
     * 列表
     * @param currentPage
     * @param per
     * @return
     * @throws Exception
     */
    @GetMapping(value="")
    public ResponseEntity index(@RequestParam(value="page", defaultValue="1") int currentPage,
                                @RequestParam(defaultValue="10") int per) throws Exception {
        Page<BannerDto> page = new Page<>(currentPage, per);
        return render(new PaginationTemplate<>(bannerService.getDtos(page, null)));
    }

    /**
     * 详情
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{id}")
    public ResponseEntity show(@PathVariable long id) throws Exception {
        BannerDto banner = bannerService.getDto(id);
        return render(new SingleTemplate<>(banner));
    }

    /**
     * 创建banner
     * @param rbo
     * @return
     * @throws Exception
     */
    @PostMapping(value="")
    public ResponseEntity create(@Valid @RequestBody BannerRbo rbo) throws Exception {
        bannerService.save(rbo.convertTo());
        return render(ResultTemplate.createOk());
    }

    /**
     * 更新banner
     * @param id
     * @param rbo
     * @return
     * @throws Exception
     */
    @PatchMapping(value="/{id}")
    public ResponseEntity update(@PathVariable long id,
                                 @Valid @RequestBody BannerRbo rbo) throws Exception {
        Banner banner = rbo.convertTo();
        banner.setId(id);
        bannerService.updateById(banner);
        return render(ResultTemplate.updateOk());
    }
}
