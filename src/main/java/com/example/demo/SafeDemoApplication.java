package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
//@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan("com.example.demo.mapper")
public class SafeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SafeDemoApplication.class, args);
    }


}

