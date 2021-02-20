package com.teslahua.shoppingmall.product.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * @Author: Zhonghua Wang
 * @Description:
 * @Date:Create：in 2021/2/18 18:10
 * @Modified By：
 */

@EnableConfigurationProperties(CacheProperties.class)
@Configuration
@EnableCaching
public class MyCacheConfig {

    /**
     * 配置文件中的东西没有生效
     * 1、原来和配置文件绑定的配置类是这样子的
     *  @ConfigurationProperties(prefix = "spring.cache")
     *  public class CachePropertis
     * 2、要让它生效需要加上：
     *  @EnableConfigurationProperties(CacheProperties.class)
     *
     * @return
     */
    @Bean
    RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties){

        //首先得到默认配置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        //设置 key 的序列化方式为 StringRedisSerializer
        config = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        //设置 value 的序列化方式为泛型的JSON序列化器 GenericFastJsonRedisSerializer
        config= config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericFastJsonRedisSerializer()));

        //将配置文件中的所有配置都生效
        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        if(redisProperties.getTimeToLive() != null){
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        if(redisProperties.getKeyPrefix() != null){
            config = config.computePrefixWith(CacheKeyPrefix.prefixed(redisProperties.getKeyPrefix()));
        }
        if(!redisProperties.isCacheNullValues()){
            config = config.disableCachingNullValues();
        }
        if(!redisProperties.isUseKeyPrefix()){
            config = config.disableKeyPrefix();
        }

        return null;
    }
}
