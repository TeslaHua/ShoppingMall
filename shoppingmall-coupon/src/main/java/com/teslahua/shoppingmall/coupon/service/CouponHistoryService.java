package com.teslahua.shoppingmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teslahua.common.utils.PageUtils;
import com.teslahua.shoppingmall.coupon.entity.CouponHistoryEntity;

import java.util.Map;

/**
 * 优惠券领取历史记录
 *
 * @author wangzhonghua
 * @email wangzhonghua@cnu.edu.cn
 * @date 2020-11-14 13:08:01
 */
public interface CouponHistoryService extends IService<CouponHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

