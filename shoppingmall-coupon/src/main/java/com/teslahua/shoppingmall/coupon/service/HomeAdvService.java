package com.teslahua.shoppingmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teslahua.common.utils.PageUtils;
import com.teslahua.shoppingmall.coupon.entity.HomeAdvEntity;

import java.util.Map;

/**
 * 首页轮播广告
 *
 * @author wangzhonghua
 * @email wangzhonghua@cnu.edu.cn
 * @date 2020-11-14 13:08:01
 */
public interface HomeAdvService extends IService<HomeAdvEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

