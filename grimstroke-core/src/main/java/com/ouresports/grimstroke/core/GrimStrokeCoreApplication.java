package com.ouresports.grimstroke.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author will
 * @date 2018/11/22
 */
@SpringBootApplication
@MapperScan("com.ouresports.grimstroke.core.mapper")
public class GrimStrokeCoreApplication {
    public static void main(String[] argvs) {
        SpringApplication.run(GrimStrokeCoreApplication.class, argvs);
    }
}
