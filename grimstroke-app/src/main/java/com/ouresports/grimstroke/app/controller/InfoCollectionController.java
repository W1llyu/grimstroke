package com.ouresports.grimstroke.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.app.base.template.PaginationTemplate;
import com.ouresports.grimstroke.app.vo.InformationVo;
import com.ouresports.grimstroke.core.dto.InformationDto;
import com.ouresports.grimstroke.core.service.InfoCollectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *
 * @author will
 * @date 2018/11/30
 */
@RestController
@RequestMapping(value="/api/info_collections", produces="application/json;charset=UTF-8")
public class InfoCollectionController extends BaseController {
    @Resource
    private InfoCollectionService infoCollectionService;

    /**
     * 专栏列表
     * @param currentPage
     * @param per
     * @param gameId
     * @return
     * @throws Exception
     */
    @GetMapping(value="")
    public ResponseEntity index(@RequestParam(value="page", defaultValue="1") int currentPage,
                                @RequestParam(defaultValue="10") int per,
                                @RequestParam(value="game_id", required=false) Integer gameId) throws Exception {
        Page<InformationDto> page = new Page<>(currentPage, per);
        QueryWrapper<InformationDto> wrapper = new QueryWrapper<InformationDto>().eq("enabled", true);
        if (gameId != null) {
            wrapper.eq("game_id", gameId);
        }
        IPage<InformationDto> informationPages = infoCollectionService.getInformationDtoPages(page, wrapper);
        return render(new PaginationTemplate(informationPages, InformationVo.class));
    }

    /**
     * 专栏下的资讯列表
     * @param id
     * @param currentPage
     * @param per
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{id}/information")
    public ResponseEntity informationOfCol(@PathVariable long id,
                                           @RequestParam(value="page", defaultValue="1") int currentPage,
                                           @RequestParam(defaultValue="10") int per) throws Exception {
        Page<InformationDto> page = new Page<>(currentPage, per);
        QueryWrapper<InformationDto> wrapper = new QueryWrapper<InformationDto>().eq("enabled", true);
        IPage<InformationDto> informationDtoIPage = infoCollectionService.getInformationDtosOfCol(page, id, wrapper);
        return render(new PaginationTemplate(informationDtoIPage, InformationVo.class));
    }
}
