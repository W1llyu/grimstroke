package com.ouresports.grimstroke.lib.service;

import com.ouresports.grimstroke.lib.GrimstrokeLibApplicationTest;
import com.ouresports.grimstroke.lib.sensiwords.SensitiveWordFilterService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by will on 2019/1/4.
 */
public class SensitiveWordFilterServiceTest extends GrimstrokeLibApplicationTest {
    @Resource
    private SensitiveWordFilterService sensitiveWordFilterService;

    @Test
    public void testFilter() {
        String str = sensitiveWordFilterService.replaceBadWord("加群看电影www.baidu.com出售迷昏药", 2, "*");
        System.out.println(str);
    }
}
