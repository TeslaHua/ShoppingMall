package com.teslahua.shoppingmall.product;

import com.teslahua.shoppingmall.product.entity.BrandEntity;
import com.teslahua.shoppingmall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ShoppingmallProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();

        // 测试修改
        brandEntity.setBrandId(1L);
        brandEntity.setDescript("TeslaHua");
        brandService.updateById(brandEntity);

        /*  测试新增
        brandEntity.setName("华为");
        brandService.save(brandEntity);
        System.out.println("保存成功......");
        */

    }

}
