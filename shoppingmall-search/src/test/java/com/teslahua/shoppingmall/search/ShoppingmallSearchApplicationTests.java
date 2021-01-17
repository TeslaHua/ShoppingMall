package com.teslahua.shoppingmall.search;

import com.alibaba.fastjson.JSON;
import com.teslahua.shoppingmall.search.config.ShoppingmallElasticSearchConfig;
import lombok.Data;
import lombok.ToString;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)   //测试就会将在Spring容器环境下进行
@SpringBootTest
public class ShoppingmallSearchApplicationTests {

    @Autowired
    private RestHighLevelClient client;

    @Data
    static class Account{
        private int account_number;
        private int balance;
        private String lastname;
        private String firstname;
        private int age;
        private String gender;
        private String employee;
        private String email;
        private String city;
        private String state;
    }
    /**
     * 测试复杂数据检索: 搜索bank索引中 address 包含 mill 的所有人的年龄分布以及平均薪资
     * @throws IOException
     */
    @Test
    public void searchData() throws IOException{
        //1、创建检索请求
        SearchRequest searchRequest = new SearchRequest();
        //指定要检索的索引
        searchRequest.indices("bank");
        //指定DSL，检索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //1.1)、构造检索条件
//        searchSourceBuilder.query();
//        searchSourceBuilder.from();
//        searchSourceBuilder.size();
//        searchSourceBuilder.aggregation();

        searchSourceBuilder.query(QueryBuilders.matchQuery("address","mill")); // query 条件
        System.out.println(searchSourceBuilder.toString()); //打印检索条件

        //1.2) 按照年龄的值分布聚合
        TermsAggregationBuilder ageAgg = AggregationBuilders.terms("ageAgg").field("age").size(10);
        searchSourceBuilder.aggregation(ageAgg);

        //1.3) 计算平均薪资
        AvgAggregationBuilder balanceAvg = AggregationBuilders.avg("balanceAgg").field("balance");
        searchSourceBuilder.aggregation(balanceAvg);
        System.out.println(searchSourceBuilder.toString()); //打印检索条件

        searchRequest.source(searchSourceBuilder);

        //2、执行检索
        SearchResponse searchResponse = client.search(searchRequest,ShoppingmallElasticSearchConfig.COMMON_OPTIONS);

        //3、分析结果，结果全在 searchResponse 里面
        System.out.println(searchResponse.toString());

        //3.1)、获取所有查到的数据
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();  //真正命中的所有记录
        for (SearchHit hit: searchHits) {
            String sourceAsString = hit.getSourceAsString();
            Account account = JSON.parseObject(sourceAsString,Account.class);
            System.out.println(account);
        }
        //3.2)、获取这次检索到的分析数据
        Aggregations aggregations = searchResponse.getAggregations();
//        for (Aggregation aggregation : aggregations.asList()) {
//            aggregation.getName();  //当前聚合名字
//        }
        Terms ageAgg1 = aggregations.get("ageAgg");
        for (Terms.Bucket bucket : ageAgg1.getBuckets()) {
            String keyAsString = bucket.getKeyAsString();
            System.out.println("年龄："+keyAsString+"==>"+bucket.getDocCount());
        }

        Avg balanceAvg1 = aggregations.get("balanceAvg");
        System.out.println("平均薪资："+balanceAvg1.getValue());
    }

    /**
     * 测试存储数据到 es，更新也可以
     * @throws IOException
     */
    @Test
    public void indexData() throws IOException{
        //新建一个索引
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1"); //数据的id为1
        //1、第一种插入数据的方法
        //indexRequest.source("userName","zhangsan","age",18,"gender","男");  // k-v 数据对
        //2、第二种插入数据的方法
        User user = new User();
        user.setUserName("zhangsan");
        user.setAge(18);
        user.setGender("男");
        String jsonString = JSON.toJSONString(user);

        indexRequest.source(jsonString, XContentType.JSON); //填入要保存的数据

        //真正的插入数据，并选择 ElasticSearch 通用配置
        IndexResponse index = client.index(indexRequest, ShoppingmallElasticSearchConfig.COMMON_OPTIONS);
    }

    @Data
    class User{
        private String userName;
        private String gender;
        private Integer age;
    }

    @Test
    public void contextLoads() {
        System.out.println(client);
    }

}
