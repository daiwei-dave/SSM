package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Items;
import org.seckill.entity.SuccessKilled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by daiwei
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class ItemsDaoTest {

    @Autowired
    private ItemsDao itemsDao;

//    @Test
//    public void testInsertSuccessKilled() throws Exception {
//        System.out.println("haha");
//        List<Items> items = itemsDao.getItemsByCart("ipad");
//        for (Items i:items
//             ) {
//            System.out.println(i.toString());
//
//        }
//    }


}