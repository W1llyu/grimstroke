package com.ouresports.grimstroke.lib.streamlink;

import com.ouresports.grimstroke.lib.exception.LibServiceException;
import org.junit.Test;

/**
 * Created by will on 2018/12/19.
 */
public class DouyuTest {
    @Test
    public void testGetStreamLink() throws LibServiceException {
        Douyu douyu = new Douyu("288016");
        System.out.println(douyu.getStreamLink());
    }
}
