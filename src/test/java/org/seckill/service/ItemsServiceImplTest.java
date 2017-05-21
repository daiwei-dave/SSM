package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by daiwei on 2017/5/14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
// 加载spring容器
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml" })
public class ItemsServiceImplTest {
    // 定义logger变量
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ItemsService itemsService;
    @Test
    public void getItemsByCart() throws Exception {
        System.out.println("hah");
        Set<Items> items = itemsService.getItemsByCart(1);
        for (Items i:items) {
            System.out.println(i.toString());
        }
    }

}