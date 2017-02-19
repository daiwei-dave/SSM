package org.seckill.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dao.cache.RedisDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

//service层组件
@Service
public class SeckillServiceImpl implements SeckillService {
	// 定义log变量
	private Log LOG = LogFactory.getLog(this.getClass());
	@Autowired
	private SeckillDao seckillDao;
	@Autowired
	private SuccessKilledDao successKilledDao;
	@Autowired
    private RedisDao redisDao;
	// md5盐值字符串,用于混淆md5
	private final String slat = "asdfasd2341242@#$@#$%$%%#@$%#@%^%^";

	public List<Seckill> getSeckillList() {
		// TODO Auto-generated method stub
		return seckillDao.queryAll(0, 5);
	}

	public Seckill getById(long seckillId) {
		// TODO Auto-generated method stub
		return seckillDao.queryById(seckillId);
	}

	/**
	 * 暴露秒杀地址DTO
	 */
	public Exposer exportSeckillUrl(long seckillId) {
		//优化点:缓存优化 超时的基础上维护一致性
        //1.访问redis
		Seckill seckill=redisDao.getSeckill(seckillId);
		if(seckill==null){
			//2.访问数据库
			seckill = seckillDao.queryById(seckillId);
			if(seckill==null){
				//秒杀未开启,且无库存
				return new Exposer(false, seckillId);
			}else{
				//3.放入redis
				String result=redisDao.putSeckill(seckill);
				System.out.println(result);
			}
		}
	
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		// 秒杀未开启
		if (nowTime.getTime() > endTime.getTime()
				|| nowTime.getTime() < startTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(),
					startTime.getTime(), endTime.getTime());
		}
		// 返回加密后的md5,不可逆(this可能会报错调试时注意)
		String md5 = this.getMD5(seckillId);
		// 秒杀开启
		return new Exposer(true, md5, seckillId);
	}

	/**
	 * 封装秒杀执行后的结果
	 * 
	 * @param md5 就如一把秒杀钥匙 使用注解控制事务的优点:
	 *             1.开发团队达成一致约定,明确标注事务方法的编程风格.
	 *            2.保证事务方法的执行时间尽可能短,不要穿插其他网络操作RPC/HTTP请求或者剥离到事务方法外部.
	 *            3.不是所有的方法都需要事务.如一些查询的service.只有一条修改操作的service.
	 */
	@Transactional
	public SeckillExecution executeSeckill(long seckillId, long userPhone,
			String md5) throws SeckillException, RepeatKillException,
			SeckillCloseException {
		//判断该seckillId的钥匙是否对应
		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillException("钥匙不对");
		}
		// 执行秒杀逻辑:1.减库存.2.记录购买行为
		Date nowtime = new Date();
		try {
			// 记录购买行为,用户商品+1
			int inserCount = successKilledDao.insertSuccessKilled(seckillId,
					userPhone);
			if (inserCount <= 0) {
				// 重复秒杀
				throw new RepeatKillException("重复秒杀");
			} else {
				// 减库存 热点商品竞争
				int updateCount = seckillDao.reduceNumber(seckillId, nowtime);
				if (updateCount <= 0) {
					// 有异常
					throw new SeckillCloseException("Seckill is closed");
				} else {
					// 秒杀成功 commit
					SuccessKilled successKilled = successKilledDao
							.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution(seckillId,
							SeckillStatEnum.SUCCESS, successKilled);
				}
			}
		} catch (SeckillCloseException e1) {
			throw e1;
		} catch (RepeatKillException e2) {
			throw e2;
		} catch (Exception e) {
			//日志上打印错误信息
			LOG.error(e.getMessage());
			// 所有的编译期异常转化为运行期异常,spring的声明式事务做rollback
			throw new SeckillException("seckill inner error:" + e.getMessage());
		}finally{
			System.out.println("haha!made in daiwei");
		}
		
		
	}

	/**
	 * 实现md5加密
	 * 
	 * @param seckillId
	 * @return
	 */
	private String getMD5(long seckillId) {
		String base = seckillId + "/" + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

}
