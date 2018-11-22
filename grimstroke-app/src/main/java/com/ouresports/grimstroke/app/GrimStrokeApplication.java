package com.ouresports.grimstroke.app;

import org.mybatis.spring.annotation.MapperScan;
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
        "com.ouresports.grimstroke.app",
        "com.ouresports.grimstroke.core"
})
@MapperScan("com.ouresports.grimstroke.core.mapper")
public class GrimStrokeApplication {
    public static void main(String[] argvs) {
        SpringApplication.run(GrimStrokeApplication.class, argvs);
    }
}
