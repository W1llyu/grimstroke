package com.ouresports.grimstroke.app.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.app.base.template.PaginationTemplate;
import com.ouresports.grimstroke.app.base.template.ResultTemplate;
import com.ouresports.grimstroke.app.base.template.SingleTemplate;
import com.ouresports.grimstroke.app.rbo.admin.InfoCollectionRbo;
import com.ouresports.grimstroke.app.vo.admin.InfoCollectionVo;
import com.ouresports.grimstroke.core.dto.InfoCollectionDto;
import com.ouresports.grimstroke.core.entity.InfoCollection;
import com.ouresports.grimstroke.core.service.InfoCollectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *
 * @author will
 * @date 2018/12/6
 */
@RestController(value="AdminInfoCollectionController")
@RequestMapping(value="/admin/info_collections", produces="application/json;charset=UTF-8")
public class InfoCollectionController extends BaseController {
    @Resource
    private InfoCollectionService infoCollectionService;

    /**
     * 列表
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
        InfoCollection infoCollection = new InfoCollection().setGameId(gameId);
        IPage<InfoCollectionDto> informationPage = infoCollectionService.getInfoCollectionDtos(page, infoCollection);
        return render(new PaginationTemplate<>(informationPage, InfoCollectionVo.class));
    }

    /**
     * 详情
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value="/{id}")
    public ResponseEntity show(@PathVariable long id) throws Exception {
        InfoCollectionDto dto = infoCollectionService.getInfoCollectionDto(id);
        return render(new SingleTemplate<>(dto, InfoCollectionVo.class));
    }

    /**
     * 创建
     * @param rbo
     * @return
     * @throws Exception
     */
    @PostMapping(value="")
    public ResponseEntity create(@Valid @RequestBody InfoCollectionRbo rbo) throws Exception {
        infoCollectionService.save(rbo.convertTo());
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
                                 @RequestBody InfoCollectionRbo rbo) throws Exception {
        InfoCollection infoCollection = rbo.convertTo();
        infoCollection.setId(id);
        infoCollectionService.updateById(infoCollection);
        return render(ResultTemplate.updateOk());
    }
}
