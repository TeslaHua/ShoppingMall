package com.teslahua.shoppingmall.product.web;

import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * @Author: Zhonghua Wang
 * @Description:
 * @Date:Create：in 2021/2/14 21:03
 * @Modified By：
 */
@Controller
public class IndexController {

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    RedisTemplate redisTemplate;

    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        //1、获取一把锁，只要锁的名字一样，就是同一把锁
        RLock lock = redissonClient.getLock("my-lock");

        //2、加锁
        lock.lock();   //阻塞式等待。默认加的锁都是30s时间
        //1)、锁的自动续期，如果业务超长，运行期间自动给锁续上新的30s。不用担心业务时间长，所自动过期被删除
        //2)、加锁的业务只要运行完成，就不会给当前锁续期，即使不手动解锁，锁默认在30s以后自动解锁
        try{
            System.out.println("加锁成功，执行业务..."+Thread.currentThread().getId());
            Thread.sleep(30000);
        }catch (Exception ex){
            System.out.println(ex);
        }finally {
            //3、解锁
            System.out.println("释放锁..."+Thread.currentThread().getId());
            lock.unlock();
        }
        return "hello";
    }

    //保证一定能读到最新数据，修改期间，写锁是一个排他锁(互斥锁)。读锁是一个共享锁
    //写锁没释放就必须等待
    //读 + 读：相当于无锁，并发读，只会在 redis 中记录好所有当前的读锁。他们都会同时加锁成功
    //写 + 读：等待写锁释放
    //写 + 写：阻塞方式
    //读 + 写：有读锁，写也需要等待
    @ResponseBody
    @GetMapping("/write")
    public String writeValue(){
        RReadWriteLock lock = redissonClient.getReadWriteLock("rw-lock");
        String s = "";
        //获取写锁
        RLock rLock = lock.writeLock();
        try{
            //1、改数据加写锁，读数据加读锁
            rLock.lock();
            s = UUID.randomUUID().toString();
            Thread.sleep(30000);
            redisTemplate.opsForValue().set("writeValue",s);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }finally {
            rLock.unlock();
        }
        return s;
    }

    @GetMapping("/read")
    @ResponseBody
    public String readValue(){
        RReadWriteLock lock = redissonClient.getReadWriteLock("rw-lock");
        String s = "";
        //加读锁
        RLock rLock = lock.readLock();
        rLock.lock();
        try{
            s = (String) redisTemplate.opsForValue().get("writeValue");
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            rLock.unlock();
        }
        return s;
    }

    /**
     * 信号量模拟车库停车
     * 信号量可以用于分布式限流
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/park")
    @ResponseBody
    public String park() throws InterruptedException {
        RSemaphore park = redissonClient.getSemaphore("park");
        //阻塞式获取
        park.acquire();  //获取一个信号，占一个车位

        /*
        boolean b = park.tryAcquire();//非阻塞式获取
        if(b){
            //TODO 执行业务
        }
        */

        return "park";
    }

    @GetMapping("/go")
    @ResponseBody
    public String go(){
        RSemaphore go = redissonClient.getSemaphore("park");
        go.release(); //释放一个车位

        return "go";
    }
}
