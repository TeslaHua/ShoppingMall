package com.teslahua.shoppingmall.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teslahua.common.utils.PageUtils;
import com.teslahua.common.utils.Query;

import com.teslahua.shoppingmall.product.dao.CategoryDao;
import com.teslahua.shoppingmall.product.entity.CategoryEntity;
import com.teslahua.shoppingmall.product.service.CategoryService;

import javax.annotation.Resource;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Resource
    public CategoryDao categoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    /**查出数据并组装成树形结构
     * @return
     */
    @Override
    public List<CategoryEntity> listWithTree() {
        //1、查出所有分类
        List<CategoryEntity> categoryEntities = categoryDao.selectList(null);
        //2、组装成父子的树形结构
        //2、1)找到所有的一级分类: parent_cid = 0
        List<CategoryEntity> level1Menu = categoryEntities.stream().filter(categoryEntity ->
                categoryEntity.getParentCid() == 0
        ).map(menu ->{
            menu.setChildren(getChildrens(menu,categoryEntities));
            return menu;
        }).sorted((menu1,menu2) -> {
            return (menu1.getSort() == null?0:menu1.getSort()) - menu2.getSort();
        }).collect(Collectors.toList());
        return categoryEntities;
    }

    //递归获取一个菜单的所有子菜单
    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all){

        List<CategoryEntity> children = all.stream().filter(categoryEntity -> {
            return categoryEntity.getParentCid() == root.getCatId();
        }).map(categoryEntity -> {
            //1、找到子菜单
            categoryEntity.setChildren(getChildrens(categoryEntity,all));
            return categoryEntity;
        }).sorted((menu1,menu2) ->{
            //2、菜单的排序
            return (menu1.getSort() == null?0:menu1.getSort()) - menu2.getSort();
        }).collect(Collectors.toList());
        return children;
    }

}