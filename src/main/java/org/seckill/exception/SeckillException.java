package org.seckill.exception;

/**
 * 秒杀相关业务异常，是开发者允许用户出现的异常
 * Created by wchb7 on 16-5-14.
 */
public class SeckillException extends RuntimeException{

	public SeckillException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SeckillException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
