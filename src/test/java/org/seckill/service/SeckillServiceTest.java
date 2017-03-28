package org.seckill.service;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 **
 * 配置Spring和Junit整合,junit启动时加载springIOC容器 spring-test,junit
 */

@RunWith(SpringJUnit4ClassRunner.class)
// 加载spring容器
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml" })
public class SeckillServiceTest {
	// 定义logger变量
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SeckillService seckillService;

	@Test
	public void testGetSeckillList() throws Exception {
		List<Seckill> list = seckillService.getSeckillList();
		logger.info("list={}", list);

	}

	@Test
	public void testGetById() throws Exception {
		long id = 1000;
		Seckill seckill = seckillService.getById(id);
		logger.info("seckill={}", seckill);
	}

	/**
	 * 测试加载spring容器
	 */
	@Test
	public void testSpring() {
		System.out.println("hello junit");
	}

	@Test
	public void testExportSeckillUrl() throws Exception {
		long id = 1002;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		logger.debug("开始执行吧");
		logger.info("exposer={}", exposer);
	}

	@Test
	public void testExecuteSeckill() {
		long id = 1000;
		long phone = 15821739111L;
		String md5 = "9b8082b22ded08718a4255e9f482a80c";
		SeckillExecution seckillExecution = seckillService.executeSeckill(id,
				phone, md5);
		logger.info("result={}", seckillExecution);
	}

	@Test
	public void testSeckillLogic() {
		long id = 1001;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		if (exposer.isExposed()) {
			long phone = 15821739222L;
			String md5 = exposer.getMd5();
			try {
				SeckillExecution seckillExecution = seckillService
						.executeSeckill(id, phone, md5);
				logger.info("result={}", seckillExecution);
			} catch (RepeatKillException e) {
				//控制台打印error信息，使用try。。。catch为编译异常，避免系统报错
				logger.error(e.getMessage());
			} catch (SeckillCloseException e) {
				logger.error(e.getMessage());
			}catch (Exception e) {
				logger.error(e.getMessage());
			}
		}else {
			logger.warn("秒杀未开启 {}",exposer.toString());
		}
	}
}
