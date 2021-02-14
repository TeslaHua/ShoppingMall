package com.teslahua.shoppingmall.product.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @Author: Zhonghua Wang
 * @Description:
 * @Date:Create：in 2021/2/14 20:05
 * @Modified By：
 */
@Configuration
public class MyRedissonConfig {
    /**
     *
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() throws IOException{
        //1、创建配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.153.129:6379");
        //2、根据 Config 创建出 RedissonClient 实例
        return Redisson.create(config);
    }
}
