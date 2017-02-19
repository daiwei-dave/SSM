package org.seckill.dao.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class RedisDaoTest {
	@Autowired
	private RedisDao redisDao;
	@Autowired
	private SeckillDao seckillDao;
	private long id = 1001;

	@Test
	public void testSeckill() throws Exception {
		// get and put前提必须先启动redis服务
		// 从缓存中获取Seckill对象
		Seckill seckill = redisDao.getSeckill(id);
//		System.out.println(seckill);
		if (seckill == null) {
			seckill = seckillDao.queryById(id);
//			System.out.println(seckill);
			if (seckill != null) {
				//Seckill 对象传递到redis缓存中
				String result = redisDao.putSeckill(seckill);
				System.out.println(result);
				seckill = redisDao.getSeckill(id);
				System.out.println(seckill);
			}
		}
	}
}
