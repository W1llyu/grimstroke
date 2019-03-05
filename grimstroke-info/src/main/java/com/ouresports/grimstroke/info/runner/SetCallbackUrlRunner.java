package com.ouresports.grimstroke.info.runner;

import com.ouresports.grimstroke.lib.aliyun.service.AliyunLiveStreamService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *
 * @author will
 * @date 2019/1/8
 */
@Component
public class SetCallbackUrlRunner implements ApplicationRunner {
    @Resource
    private AliyunLiveStreamService aliyunLiveStreamService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        aliyunLiveStreamService.setLiveStreamNotifyUrl();
    }
}
