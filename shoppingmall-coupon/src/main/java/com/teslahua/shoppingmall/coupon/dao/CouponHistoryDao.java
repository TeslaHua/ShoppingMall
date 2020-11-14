package com.teslahua.shoppingmall.coupon.dao;

import com.teslahua.shoppingmall.coupon.entity.CouponHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券领取历史记录
 * 
 * @author wangzhonghua
 * @email wangzhonghua@cnu.edu.cn
 * @date 2020-11-14 13:08:01
 */
@Mapper
public interface CouponHistoryDao extends BaseMapper<CouponHistoryEntity> {
	
}
