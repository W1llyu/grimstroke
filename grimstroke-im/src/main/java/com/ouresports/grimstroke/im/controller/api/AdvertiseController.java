package com.ouresports.grimstroke.im.controller.api;

import com.ouresports.grimstroke.base.template.ResultTemplate;
import com.ouresports.grimstroke.base.template.SingleTemplate;
import com.ouresports.grimstroke.im.rbo.admin.AdvertiseRbo;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *
 * @author will
 * @date 2019/1/8
 */
@RestController("AdvertiseController")
@RequestMapping(value="/api/advertisement", produces="application/json;charset=UTF-8")
public class AdvertiseController extends BaseController {
    private static final String ADVERTISE_KEY = "grimstroke.advertise.url";
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获得广告地址
     * @return
     * @throws Exception
     */
    @GetMapping(value="")
    public ResponseEntity getAdvertisement() throws Exception {
        String url = stringRedisTemplate.opsForValue().get(ADVERTISE_KEY);
        return render(new SingleTemplate<>(url));
    }

    /**
     * 更新广告地址
     * @param rbo
     * @return
     * @throws Exception
     */
    @PatchMapping(value="")
    public ResponseEntity updateAdvertisement(@RequestBody @Valid AdvertiseRbo rbo) throws Exception {
        stringRedisTemplate.opsForValue().set(ADVERTISE_KEY, rbo.getUrl());
        return render(ResultTemplate.updateOk());
    }
}
