package com.teslahua.shoppingmall.product;

import com.teslahua.shoppingmall.product.entity.BrandEntity;
import com.teslahua.shoppingmall.product.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class ShoppingmallProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();

        // 测试修改
        brandEntity.setBrandId(1L);
        brandEntity.setDescript("TeslaHua");
        brandService.updateById(brandEntity);

        /*  测试新增
        brandEntity.setName("华为");
        brandService.save(brandEntity);
        System.out.println("保存成功......");
        */

    }

    @Test
    public void teststringRedisTemplate(){
        ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();
        //保存
        ops.set("hello","world_"+ UUID.randomUUID().toString());

        //查询
        String hello = ops.get("hello");
        System.out.println("之前保存的数据是："+hello);
    }

    //测试 Redisson
    @Test
    public void testRedisson(){
        System.out.println(redissonClient);
    }


    @Test
    public void createExchange(){
        //DirectExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments)
        DirectExchange directExchange = new DirectExchange("hello-java-exchange",true,false);
        //声明一个接口
        amqpAdmin.declareExchange(directExchange);
        log.info("Exchange[{}] 创建成功","hello-java-exchange");
    }


    @Test
    public void createQueue(){
        //Queue(String name, boolean durable, boolean exclusive, boolean autoDelete, @Nullable Map<String, Object> arguments)
        Queue queue = new Queue("hello-java-queue",true,false,true);
        //声明一个队列
        amqpAdmin.declareQueue(queue);
        log.info("Queue[{}] 创建成功","hello-java-queue");
    }

    @Test
    public void createBinding(){
        //Binding(String destination, Binding.DestinationType destinationType, String exchange, String routingKey, @Nullable Map<String, Object> arguments)
        /*
          destination【目的地】：
          destinationType【目的地类型】：
          exchange【交换机】：
          routingKey【路由键】：
          将exchange指定的交换机和destinationType类型的destination进行绑定，使用routingKey作为路由键。
         */
        Binding binding = new Binding("hello-java-queue", Binding.DestinationType.QUEUE,"hello-java-exchange","hello.java",null);
        //声明一个连接
        amqpAdmin.declareBinding(binding);
    }
}
