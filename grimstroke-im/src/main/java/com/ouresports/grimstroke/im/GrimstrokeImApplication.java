package com.ouresports.grimstroke.im;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author will
 * @date 2018/12/18
 */
@SpringBootApplication
@ComponentScan(basePackages={
        "com.ouresports.grimstroke.im",
        "com.ouresports.grimstroke.base",
        "com.ouresports.grimstroke.lib"
})
public class GrimstrokeImApplication {
    public static void main(String[] argvs) {
        SpringApplication.run(GrimstrokeImApplication.class, argvs);
    }
}
