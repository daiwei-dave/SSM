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

//service�����
@Service
public class SeckillServiceImpl implements SeckillService {
	// ����log����
	private Log LOG = LogFactory.getLog(this.getClass());
	@Autowired
	private SeckillDao seckillDao;
	@Autowired
	private SuccessKilledDao successKilledDao;
	@Autowired
    private RedisDao redisDao;
	// md5��ֵ�ַ���,���ڻ���md5
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
	 * ��¶��ɱ��ַDTO
	 */
	public Exposer exportSeckillUrl(long seckillId) {
		//�Ż���:�����Ż� ��ʱ�Ļ�����ά��һ����
        //1.����redis
		Seckill seckill=redisDao.getSeckill(seckillId);
		if(seckill==null){
			//2.�������ݿ�
			seckill = seckillDao.queryById(seckillId);
			if(seckill==null){
				//��ɱδ����,���޿��
				return new Exposer(false, seckillId);
			}else{
				//3.����redis
				String result=redisDao.putSeckill(seckill);
				System.out.println(result);
			}
		}
	
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		// ��ɱδ����
		if (nowTime.getTime() > endTime.getTime()
				|| nowTime.getTime() < startTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(),
					startTime.getTime(), endTime.getTime());
		}
		// ���ؼ��ܺ��md5,������(this���ܻᱨ�����ʱע��)
		String md5 = this.getMD5(seckillId);
		// ��ɱ����
		return new Exposer(true, md5, seckillId);
	}

	/**
	 * ��װ��ɱִ�к�Ľ��
	 * 
	 * @param md5 ����һ����ɱԿ�� ʹ��ע�����������ŵ�:
	 *             1.�����ŶӴ��һ��Լ��,��ȷ��ע���񷽷��ı�̷��.
	 *            2.��֤���񷽷���ִ��ʱ�価���ܶ�,��Ҫ���������������RPC/HTTP������߰��뵽���񷽷��ⲿ.
	 *            3.�������еķ�������Ҫ����.��һЩ��ѯ��service.ֻ��һ���޸Ĳ�����service.
	 */
	@Transactional
	public SeckillExecution executeSeckill(long seckillId, long userPhone,
			String md5) throws SeckillException, RepeatKillException,
			SeckillCloseException {
		//�жϸ�seckillId��Կ���Ƿ��Ӧ
		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillException("Կ�ײ���");
		}
		// ִ����ɱ�߼�:1.�����.2.��¼������Ϊ
		Date nowtime = new Date();
		try {
			// ��¼������Ϊ,�û���Ʒ+1
			int inserCount = successKilledDao.insertSuccessKilled(seckillId,
					userPhone);
			if (inserCount <= 0) {
				// �ظ���ɱ
				throw new RepeatKillException("�ظ���ɱ");
			} else {
				// ����� �ȵ���Ʒ����
				int updateCount = seckillDao.reduceNumber(seckillId, nowtime);
				if (updateCount <= 0) {
					// ���쳣
					throw new SeckillCloseException("Seckill is closed");
				} else {
					// ��ɱ�ɹ� commit
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
			//��־�ϴ�ӡ������Ϣ
			LOG.error(e.getMessage());
			// ���еı������쳣ת��Ϊ�������쳣,spring������ʽ������rollback
			throw new SeckillException("seckill inner error:" + e.getMessage());
		}finally{
			System.out.println("haha!made in daiwei");
		}
		
		
	}

	/**
	 * ʵ��md5����
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
