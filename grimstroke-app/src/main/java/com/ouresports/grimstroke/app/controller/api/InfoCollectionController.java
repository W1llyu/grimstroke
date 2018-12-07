package com.ouresports.grimstroke.app.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.app.base.template.PaginationTemplate;
import com.ouresports.grimstroke.app.base.template.SingleTemplate;
import com.ouresports.grimstroke.app.vo.api.InfoCollectionVo;
import com.ouresports.grimstroke.app.vo.api.InformationVo;
import com.ouresports.grimstroke.core.dto.InfoCollectionDto;
import com.ouresports.grimstroke.core.dto.InformationDto;
import com.ouresports.grimstroke.core.entity.InfoCollection;
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
        Page<InfoCollectionDto> page = new Page<>(currentPage, per);
        QueryWrapper<InfoCollection> wrapper = new QueryWrapper<>(generateGeneralQuery().setGameId(gameId))
                .orderByDesc("`info_collections`.`sticky`")
                .orderByDesc("`created_at`");
        IPage<InfoCollectionDto> informationPage = infoCollectionService.getDtos(page, wrapper);
        return render(new PaginationTemplate<>(informationPage, InfoCollectionVo.class));
    }

    /**
     * 专栏详情
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{id}")
    public ResponseEntity show(@PathVariable long id) throws Exception {
        InfoCollection infoCollection = generateGeneralQuery();
        infoCollection.setId(id);
        InfoCollectionDto dto = infoCollectionService.getDto(new QueryWrapper<>(infoCollection));
        return render(new SingleTemplate<>(dto, InfoCollectionVo.class));
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
        IPage<InformationDto> infoDtos = infoCollectionService.getInformationDtoOfCol(page, id);
        return render(new PaginationTemplate<>(infoDtos, InformationVo.class));
    }

    private InfoCollection generateGeneralQuery() {
        return new InfoCollection().setEnabled(true);
    }
}
