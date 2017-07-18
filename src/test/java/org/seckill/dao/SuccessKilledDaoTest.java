package org.seckill.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wchb7 on 16-5-9.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Test
    public void testInsertSuccessKilled() throws Exception {
    	long id = 1002L;
        long phone = 15811112222L;
        int insertCount = successKilledDao.insertSuccessKilled(id, phone);
        System.out.println("insertCount: " + insertCount);
    }

    @Test
    public void testQueryByIdWithSeckill() throws Exception {

        long id = 1002L;
        long phone = 15811112222L;
        SuccessKilled successKilled=successKilledDao.queryByIdWithSeckill(id, phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }


    /**
     * 测试mybatis二级缓存
     * @throws Exception
     */
    @Test
    public void testCache2() throws Exception {
        long id = 1002L;
        long phone = 15811112222L;
        SuccessKilled successKilled=successKilledDao.queryByIdWithSeckill(id, phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
        //第二次查询时从缓存中读取
        SuccessKilled successKilled2=successKilledDao.queryByIdWithSeckill(id, phone);
        System.out.println(successKilled2);
    }
}