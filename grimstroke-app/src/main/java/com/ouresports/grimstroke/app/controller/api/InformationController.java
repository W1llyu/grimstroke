package com.ouresports.grimstroke.app.controller.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ouresports.grimstroke.app.base.template.PaginationTemplate;
import com.ouresports.grimstroke.core.service.InformationService;
import com.ouresports.grimstroke.app.vo.api.InformationVo;
import com.ouresports.grimstroke.core.dto.InformationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 * @author will
 * @date 2018/11/29
 */
@RestController
@RequestMapping(value="/api/information", produces="application/json;charset=UTF-8")
public class InformationController extends BaseController {
    @Resource
    private InformationService informationService;

    /**
     * 首页信息流列表
     * @param currentPage
     * @param gameId
     * @return
     * @throws Exception
     */
    @GetMapping(value="")
    public ResponseEntity index(@RequestParam(value="page", defaultValue="1") int currentPage,
                                @RequestParam(value="game_id", required=false) Integer gameId) throws Exception {
        IPage<InformationDto> dtos = informationService.getOrderedInformation(currentPage, gameId);
        return render(new PaginationTemplate<>(dtos, InformationVo.class));
    }
}
