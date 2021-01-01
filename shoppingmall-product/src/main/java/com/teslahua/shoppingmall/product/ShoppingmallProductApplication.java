package com.teslahua.shoppingmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.teslahua.shoppingmall.product.dao")
@SpringBootApplication
public class ShoppingmallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingmallProductApplication.class, args);
    }

}
