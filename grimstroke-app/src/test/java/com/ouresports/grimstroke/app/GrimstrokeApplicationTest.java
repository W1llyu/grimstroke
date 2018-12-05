package com.ouresports.grimstroke.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by will on 2018/11/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= GrimstrokeApplication.class)
@ConfigurationProperties
public class GrimstrokeApplicationTest {
    @Test
    public void test() {}
}
