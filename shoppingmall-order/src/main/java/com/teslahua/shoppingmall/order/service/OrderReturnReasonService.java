package com.teslahua.shoppingmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teslahua.common.utils.PageUtils;
import com.teslahua.shoppingmall.order.entity.OrderReturnReasonEntity;

import java.util.Map;

/**
 * 退货原因
 *
 * @author wangzhonghua
 * @email wangzhonghua@cnu.edu.cn
 * @date 2020-11-14 13:34:02
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

