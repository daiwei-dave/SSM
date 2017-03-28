package org.seckill.dao.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seckill.entity.Seckill;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class RedisDao {
	private final Log LOG = LogFactory.getLog(this.getClass());
	//�൱�����ݿ�����ӳ�
	private final JedisPool jedisPool;

	private RuntimeSchema<Seckill> schema = RuntimeSchema
			.createFrom(Seckill.class);
	/**
	 * ���ӱ��ص� Redis ����
	 * @param ip
	 * @param port
	 */
	public RedisDao(String ip, int port) {
		jedisPool = new JedisPool(ip, port);
	}
	/**
	 * ��ȡSeckill ����
	 * @param seckillId
	 * @return
	 */
	public Seckill getSeckill(long seckillId) {
		// redis�߼�����
		try {
			//��ȡJedisʵ��
			Jedis jedis = jedisPool.getResource();
			try {
				String key = "seckill:" + seckillId;
				// ��û��ʵ���ڲ����л�����
				// get:byte[]->�����л�->Object(Seckill)
				// �����Զ������л�
				byte[] bytes = jedis.get(key.getBytes());
				if (bytes != null) {
					// �ն���
					Seckill seckill = schema.newMessage();
					ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
					// seckill �������л�
					return seckill;
				}
			} finally {
				//�ͷ�jedis��Դ
				jedis.close();
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}
	/**
	 * Seckill ���󴫵ݵ�redis��
	 * @param seckill
	 * @return �ɹ�����OK��ʧ���򷵻�null
	 */
	public String putSeckill(Seckill seckill) {
		//set:Object(Seckill)->���л�->byte[] ->���͸�redis
		try{
			Jedis jedis=jedisPool.getResource();
			try{
				String key = "seckill:" + seckill.getSeckillId();
				byte[] bytes=ProtostuffIOUtil.toByteArray(seckill,
						schema,LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
				//��ʱ����,1Сʱ
				int timeOut = 60 * 60;
				String result=jedis.setex(key.getBytes(), timeOut, bytes);
				return result;		
			}finally{
				jedis.close();
			}
		}catch (Exception e) {
            LOG.error(e.getMessage());
		return null;		
	}
	}
}
