package com.teslahua.shoppingmall.member.feign;

import com.teslahua.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Zhonghua Wang
 * @Description:
 * @Date:Create：in 2020/11/14 21:15
 * @Modified By：
 */

@FeignClient("shoppingmall-coupon")  //1、先找要调用的在Nacos注册的远程服务的位置
public interface CouponFeignService {
    @RequestMapping("/coupon/coupon/member/list") //2、调用远程服务的该请求对应的方法
    public R membercoupons();
}
