package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by daiwei on 2017/5/14
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class CartDaoTest {

    @Autowired
    private CartDao cartDao;

    @Test
    public void addCart() throws Exception {
        System.out.println("haha");
        cartDao.addCart(13,2);

    }


}