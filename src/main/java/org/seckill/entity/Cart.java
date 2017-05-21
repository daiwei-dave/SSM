package org.seckill.entity;

import org.seckill.dao.ItemsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * 购物车
 */
@Component
public class Cart {

	private long id;
	private long itemsID;
	private long uID;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getItemsID() {
		return itemsID;
	}

	public void setItemsID(long itemsID) {
		this.itemsID = itemsID;
	}

	public long getuID() {
		return uID;
	}

	public void setuID(long uID) {
		this.uID = uID;
	}

	private HashMap<Items,Integer> goods;
	@Autowired
	private ItemsDao itemsDao;
	private double totalPrice;


	public Cart()
	{
		goods = new HashMap<Items,Integer>();
		totalPrice = 0.0;
	}
	
	
	public HashMap<Items, Integer> getGoods() {
		return goods;
	}

	public void setGoods(HashMap<Items, Integer> goods) {
		this.goods = goods;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	

	public boolean addGoodsInCart(Items item ,int number)
	{
		if(goods.containsKey(item))
		{
			goods.put(item, goods.get(item)+number);
		}
		else
		{
			goods.put(item, number);	
		}
		calTotalPrice();
		return true;
	}
	

	public boolean removeGoodsFromCart(Items item)
	{
		goods.remove(item);
		calTotalPrice();
		return true;
	}
	

	public double calTotalPrice()
	{
		double sum = 0.0;
		Set<Items> keys = goods.keySet();
		Iterator<Items> it = keys.iterator();
	    while(it.hasNext())
	    {
	    	Items i = it.next();
	    	sum += i.getPrice()* goods.get(i);
	    }
	    this.setTotalPrice(sum);
	    return this.getTotalPrice();
	}
	
//	public void getItems(){
//
//
//		Items i1 = new Items(1,"ipad5","beijing",200,500,"001.jpg");
//		Items i2 = new Items(2,"ipad5","chengdu",300,500,"002.jpg");
//		Items i3 = new Items(1,"ipad5","guangzhou",200,500,"001.jpg");
//
//		Cart c = new Cart();
//		c.addGoodsInCart(i1, 1);
//		c.addGoodsInCart(i2, 2);
//
//		c.addGoodsInCart(i3, 3);
//
//
//
//		Set<Map.Entry<Items, Integer>> items= c.getGoods().entrySet();
//		for(Map.Entry<Items, Integer> obj:items)
//		{
//			System.out.println(obj.getKey().getName());
//			List<Items> listItem =itemsDao.getItemsByCart(obj.getKey().getName());
//		}
//
//
//		System.out.println(c.getTotalPrice());
//
//	}

	public static void main(String[] args) {
		Cart cart=new Cart();
	//	cart.getItems();
	}
}
