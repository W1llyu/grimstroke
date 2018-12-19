package com.ouresports.grimstroke.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author will
 * @date 2018/12/17
 */
@SpringBootApplication
@ComponentScan(basePackages={
        "com.ouresports.grimstroke.info"
})
public class GrimstrokeApplication {
    public static void main(String[] argvs) {
        SpringApplication.run(GrimstrokeApplication.class, argvs);
    }
}
