package com.teslahua.shoppingmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teslahua.common.utils.PageUtils;
import com.teslahua.shoppingmall.member.entity.MemberReceiveAddressEntity;

import java.util.Map;

/**
 * 会员收货地址
 *
 * @author wangzhonghua
 * @email wangzhonghua@cnu.edu.cn
 * @date 2020-11-14 13:22:30
 */
public interface MemberReceiveAddressService extends IService<MemberReceiveAddressEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

