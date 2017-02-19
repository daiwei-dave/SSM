package org.seckill.dto;

import java.io.Serializable;
/**
 *
 * @author daiwei
 * DTO:完成WEB层到Service层的数据传递
 * 所有的ajax请求的返回类型封装JSON结果
 * @param <T> 存储的泛型
 */
public class SeckillResult<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean success;
	private T data;
	private String error;
	public SeckillResult(boolean success, String error) {
		super();
		this.success = success;
		this.error = error;
	}
	/**
	 *
	 * @param success 秒杀成功
	 * @param data 封装结果
	 */
	public SeckillResult(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "SeckillResult [success=" + success + ", data=" + data
				+ ", error=" + error + "]";
	}
}
