package com.teslahua.shoppingmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teslahua.common.utils.PageUtils;
import com.teslahua.shoppingmall.order.entity.PaymentInfoEntity;

import java.util.Map;

/**
 * 支付信息表
 *
 * @author wangzhonghua
 * @email wangzhonghua@cnu.edu.cn
 * @date 2020-11-14 13:34:02
 */
public interface PaymentInfoService extends IService<PaymentInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

