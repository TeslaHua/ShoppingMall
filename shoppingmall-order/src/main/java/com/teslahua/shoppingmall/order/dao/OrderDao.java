package com.teslahua.shoppingmall.order.dao;

import com.teslahua.shoppingmall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author wangzhonghua
 * @email wangzhonghua@cnu.edu.cn
 * @date 2020-11-14 13:34:02
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
