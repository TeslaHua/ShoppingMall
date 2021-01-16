package com.teslahua.shoppingmall.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Zhonghua Wang
 * @Description:
 * @Date:Create：in 2021/1/16 23:51
 * @Modified By：
 */
/**
 * 1、导入依赖
 * 2、编写配置，给容器中注入一个 RestHighLevelClient
 * 3、
 */
@Configuration
public class ShoppingmallElasticSearchConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.153.129",9200,"http")));
        return client;   //返回一个 RestHighLevelClient 实例
    }
}
