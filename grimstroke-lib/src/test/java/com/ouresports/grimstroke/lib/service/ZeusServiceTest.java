package com.ouresports.grimstroke.lib.service;

import com.ouresports.grimstroke.lib.GrimstrokeLibApplicationTest;
import com.ouresports.grimstroke.lib.exception.LibServiceException;
import com.ouresports.grimstroke.lib.zeus.entity.RewardRbo;
import com.ouresports.grimstroke.lib.zeus.service.ZeusService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by will on 2019/1/10.
 */
public class ZeusServiceTest extends GrimstrokeLibApplicationTest {
    @Resource
    private ZeusService zeusService;

    @Test
    public void testCreateReward() throws LibServiceException {
        zeusService.createReward("DLjYdh_QylMFFtRQO7EuM1UMgJg", new RewardRbo().setReward(new RewardRbo.Info().setAmount(1000000).setReceiverId(1L).setType("GoldReward")));
    }
}
