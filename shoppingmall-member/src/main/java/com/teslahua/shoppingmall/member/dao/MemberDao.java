package com.teslahua.shoppingmall.member.dao;

import com.teslahua.shoppingmall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author wangzhonghua
 * @email wangzhonghua@cnu.edu.cn
 * @date 2020-11-14 13:22:30
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
