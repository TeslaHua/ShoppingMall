package com.teslahua.shoppingmall.order.dao;

import com.teslahua.shoppingmall.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 * 
 * @author wangzhonghua
 * @email wangzhonghua@cnu.edu.cn
 * @date 2020-11-14 13:34:02
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {
	
}
