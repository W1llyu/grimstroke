package com.ouresports.grimstroke.core.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.ouresports.grimstroke.core.base.injector.ApplicationInjector;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.ouresports.grimstroke.core.base.constant.ApplicationConstant.*;


/**
 *
 * @author will
 * @date 2018/11/22
 */
@Configuration
public class MyBatisPlusConfiguration {
    /**
     * SQL执行效率插件
     */
    @Bean
    @Profile({"core-local","core-staging"})
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage(MAPPER_PACKAGE);
        return scannerConfigurer;
    }

    @Bean
    public ISqlInjector sqlInjector() {
        return new ApplicationInjector();
    }
}
