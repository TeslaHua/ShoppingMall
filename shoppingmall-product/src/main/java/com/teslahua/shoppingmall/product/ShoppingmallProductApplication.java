package com.teslahua.shoppingmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@MapperScan("com.teslahua.shoppingmall.product.dao")
@SpringBootApplication
@EnableDiscoveryClient
@EnableRabbit
public class ShoppingmallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingmallProductApplication.class, args);
    }

}
