package com.teslahua.shoppingmall.product.dao;

import com.teslahua.shoppingmall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author wangzhonghua
 * @email wangzhonghua@cnu.edu.cn
 * @date 2020-11-08 17:29:35
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
