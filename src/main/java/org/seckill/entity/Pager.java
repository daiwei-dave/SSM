package org.seckill.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 分页组件
 * @author daiwei
 * @date 2017/7/18
 * @params
 */
public class Pager<T> implements Serializable {

	private static final long serialVersionUID = -8741766802354222579L;
	
	private int pageSize; // 每页显示多少条记录
	
	private int currentPage; //当前第几页数据
	
	private int totalRecord; // 一共多少条记录
	
	private int totalPage; // 一共多少页记录

	//List<T>是ArrayList类的泛型，在使用时确定
	private List<T> dataList; //要显示的数据

	/**
	 * subList方式实现分页的构造方法
	 * @param pageNum 查询第几页数据
	 * @param pageSize
	 * @param sourceList
	 */
	public Pager(int pageNum, int pageSize, List<T> sourceList){
		if(sourceList == null || sourceList.isEmpty()){
			return;
		}
		
		// 总记录条数
		this.totalRecord = sourceList.size();
		
		// 每页显示多少条记录
		this.pageSize = pageSize;

		//获取总页数,如果整除表示正好分N页，如果不能整除在N页的基础上+1页
		this.totalPage = this.totalRecord%this.pageSize==0? this.totalRecord/this.pageSize : (this.totalRecord/this.pageSize)+1;

		//判断当前页是否越界,如果越界，我们就查最后一页或者第一页
		this.currentPage = this.totalPage < pageNum ?  this.totalPage : pageNum;
		if(pageNum<=0){
			this.currentPage=1;
		}
				
		// 起始索引
		int fromIndex	= this.pageSize * (this.currentPage -1);
		
		// 结束索引
		int toIndex  = this.pageSize * this.currentPage > this.totalRecord ? this.totalRecord : this.pageSize * this.currentPage;
		//要显示的数据		
		this.dataList = sourceList.subList(fromIndex, toIndex);
	}
	
	public Pager(){
		
	}
	/**
	 * sql方式实现分页的构造方法
	 * @param pageSize
	 * @param currentPage
	 * @param totalRecord
	 * @param totalPage
	 * @param dataList
	 */
	public Pager(int pageSize, int currentPage, int totalRecord, int totalPage,
			List<T> dataList) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		this.totalPage = totalPage;
		this.dataList = dataList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	@Override
	public String toString() {
		return "Pager [pageSize=" + pageSize + ", currentPage=" + currentPage
				+ ", totalRecord=" + totalRecord + ", totalPage=" + totalPage
				+ ", dataList=" + dataList + "]";
	}

}
