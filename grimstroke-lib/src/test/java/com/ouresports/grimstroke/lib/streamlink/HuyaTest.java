package com.ouresports.grimstroke.lib.streamlink;

import com.ouresports.grimstroke.lib.exception.LibServiceException;
import org.junit.Test;

/**
 * Created by will on 2018/12/19.
 */
public class HuyaTest {
    @Test
    public void testGetStreamLink() throws LibServiceException {
        Huya huya = new Huya("dongji");
        System.out.println(huya.getStreamLink());
    }
}
