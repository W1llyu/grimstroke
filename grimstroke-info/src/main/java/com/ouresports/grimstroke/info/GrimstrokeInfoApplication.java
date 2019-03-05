package com.ouresports.grimstroke.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author will
 * @date 2018/11/22
 */
@SpringBootApplication
@ComponentScan(basePackages={
        "com.ouresports.grimstroke.info",
        "com.ouresports.grimstroke.base",
        "com.ouresports.grimstroke.lib"
})
public class GrimstrokeInfoApplication {
    public static void main(String[] argvs) {
        SpringApplication.run(GrimstrokeInfoApplication.class, argvs);
    }
}
