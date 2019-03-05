package com.ouresports.grimstroke.im;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by will on 2018/12/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=GrimstrokeImApplication.class)
@ConfigurationProperties
public class GrimstrokeImApplicationTest {
    @Test
    public void test() {}
}
