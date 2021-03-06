package com.ouresports.grimstroke.info.controller.admin;

import com.ouresports.grimstroke.base.annotation.*;
import com.ouresports.grimstroke.base.template.ResultTemplate;
import com.ouresports.grimstroke.info.rbo.admin.InfoCollectionRbo;
import com.ouresports.grimstroke.info.rbo.admin.InformationRbo;
import com.ouresports.grimstroke.info.vo.admin.InfoCollectionVo;
import com.ouresports.grimstroke.info.entity.InfoCollection;
import com.ouresports.grimstroke.info.service.InfoCollectionService;
import com.ouresports.grimstroke.info.service.InfoCollectionsInformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *
 * @author will
 * @date 2018/12/6
 */
@RestCrudController(value="AdminInfoCollectionController")
@RequestMapping(value="/admin/info_collections", produces="application/json;charset=UTF-8")
@RestIndex(dto=true, voClass=InfoCollectionVo.class)
@RestShow(dto=true, voClass=InfoCollectionVo.class)
@RestCreate(rboClass=InfoCollectionRbo.class)
@RestUpdate(rboClass=InfoCollectionRbo.class, valid=false)
@AuthToken
public class InfoCollectionController extends BaseController<InfoCollection, InfoCollectionService> {
    @Resource
    private InfoCollectionsInformationService infoCollectionsInformationService;

    /**
     * 给专栏添加信息流
     * @param id
     * @param rbo
     * @return
     * @throws Exception
     */
    @PostMapping(value="/{id}/information")
    public ResponseEntity addInformation(@PathVariable long id,
                                         @Valid @RequestBody InformationRbo rbo) throws Exception {
        infoCollectionsInformationService.addInformation(baseService.find(id), rbo.convertTo());
        return render(ResultTemplate.createOk());
    }

    /**
     * 从专栏删除信息流
     * @param id
     * @param rbo
     * @return
     * @throws Exception
     */
    @PostMapping(value="/{id}/remove_information")
    public ResponseEntity removeInformation(@PathVariable long id,
                                            @Valid @RequestBody InformationRbo rbo) throws Exception {
        infoCollectionsInformationService.removeInformation(baseService.find(id), rbo.convertTo());
        return render(ResultTemplate.deleteOk());
    }
}
