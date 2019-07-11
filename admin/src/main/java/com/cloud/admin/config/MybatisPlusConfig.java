package com.cloud.admin.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.cloud.*.dao")
public class MybatisPlusConfig {
    @Value("${server.product}")
    private boolean product;
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        if (product)
            return null;
        return new PerformanceInterceptor();
    }
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
