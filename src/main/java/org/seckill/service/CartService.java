package org.seckill.service;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Cart;
import org.seckill.entity.Items;

import java.util.List;

/**
 * Created by daiwei on 2017/5/14.
 */
public interface CartService {


    /**
     * 添加进购物车,通过设置主建冲突避免添加重复的商品
     * @param itemsID
     * @param uID
     * @return
     */
    int addCart(long itemsID,long uID);



    /**
     * 查看我的购物车
     * @param uID 用户id
     * @return
     */
    List<Items> findMyCart(long uID);

    Cart findMyCart(long uID, long itemsID);
}
