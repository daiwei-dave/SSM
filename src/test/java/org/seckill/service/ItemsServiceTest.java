package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Items;
import org.seckill.entity.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by daiwei on 2017/7/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 加载spring容器
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml" })
public class ItemsServiceTest {
    // 定义logger变量
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ItemsService itemsService;
    @Test
    public void getAllItems() throws Exception {
        List<Items> allItems = itemsService.getAllItems();
        for (int i = 0; i <allItems.size() ; i++) {
            System.out.println(allItems.get(i).toString());
        }
    }

    @Test
    public void getAllItems1() throws Exception {
        Pager<Items> allItems = itemsService.getAllItems(new Items(), 0, 3);
        for (int i = 0; i <allItems.getData().size() ; i++) {
            System.out.println(allItems.getData().get(i).toString());
        }
    }

}