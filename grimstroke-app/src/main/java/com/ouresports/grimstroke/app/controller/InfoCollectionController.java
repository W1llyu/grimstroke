package com.ouresports.grimstroke.app.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.app.base.template.PaginationTemplate;
import com.ouresports.grimstroke.app.base.template.SingleTemplate;
import com.ouresports.grimstroke.app.vo.InfoCollectionVo;
import com.ouresports.grimstroke.app.vo.InformationVo;
import com.ouresports.grimstroke.core.dto.InfoCollectionDto;
import com.ouresports.grimstroke.core.dto.InformationDto;
import com.ouresports.grimstroke.core.service.InfoCollectionService;
import org.apache.ibatis.javassist.NotFoundException;
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
        IPage<InfoCollectionDto> informationPage = infoCollectionService.getInfoCollectionDtos(page, gameId);
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
        InfoCollectionDto dto = infoCollectionService.getInfoCollectionDto(id);
        if (dto == null) {
            throw new NotFoundException("InfoCollection");
        }
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
}
