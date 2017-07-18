package org.seckill.service.impl;

import org.seckill.dao.CartDao;
import org.seckill.dao.ItemsDao;
import org.seckill.entity.Cart;
import org.seckill.entity.Items;
import org.seckill.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daiwei on 2017/5/16.
 */
@Component
public class CartServiceImpl implements CartService{

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ItemsDao itemsDao;



    public int addCart(long itemsID, long uID) {
        return cartDao.addCart(itemsID, uID);
    }

    public List<Items> findMyCart(long uID) {

        List<Cart> cart = cartDao.findMyCart(uID);
        List<Items> listItem=new ArrayList<Items>();
        //遍历购物车
        for (Cart c:cart) {
            //通过商品id获取商品,并添加进list集合
            listItem.add(itemsDao.findItemsById(c.getItemsID()));
        }
        return listItem;
    }

    public Cart findMyCart(long uID, long itemsID) {
        return cartDao.findMyCartByUIdAndItemsID(uID,itemsID);
    }
}
