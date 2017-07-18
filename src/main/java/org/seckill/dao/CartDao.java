package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Cart;
import org.seckill.entity.Items;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by daiwei on 2017/5/14.
 */
@Component
public interface CartDao {
    //添加进购物车
    int addCart(@Param("itemsID") long itemsID, @Param("uID") long uID);

    List<Cart> findMyCart(long uID);

    /**
     * 通过uID和itemsID查看购物车
     * @param uID
     * @param itemsID
     * @return
     */
    Cart findMyCartByUIdAndItemsID(@Param("uID")long uID,@Param("itemsID")long itemsID);
}
