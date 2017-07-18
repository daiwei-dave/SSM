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

/**
 * Created by daiwei on 2017/5/14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
// 加载spring容器
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml" })
public class CartServiceImplTest {
    // 定义logger变量
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CartService cartService;
    @Test
    public void getItemsByCart() throws Exception {
        System.out.println("hah");
    }
    @Test
    public void addCart() throws Exception {
        cartService.addCart(11,4);
    }

    @Test
    public void findMyCart() throws Exception {
        List<Items> itemsList = cartService.findMyCart(1);
        for (Items list:itemsList) {
            System.out.println(list.toString());
        }
    }
}