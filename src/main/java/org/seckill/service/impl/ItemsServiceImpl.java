package org.seckill.service.impl;

import org.seckill.dao.ItemsDao;
import org.seckill.entity.Cart;
import org.seckill.entity.Items;
import org.seckill.entity.Pager;
import org.seckill.service.CartService;
import org.seckill.service.ItemsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by daiwei on 2017/5/14.
 */
@Component
public class ItemsServiceImpl implements ItemsService{
    // ����logger����
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private HashMap<Items,Integer> goods;
 //   List<Items> listItem=new ArrayList<Items>();
    Set<Items> itemsSet=new HashSet<Items>();
    @Autowired
    private ItemsDao itemsDao;

    @Autowired
    private CartService cartService;

    /**
     * ͨ�����ﳵ��ȡ������Ϣ
     * @return
     */
    public Set<Items> getItemsByCart(long uID) {

        Set<Items> items=new HashSet<Items>();
        List<Items> itemsList = cartService.findMyCart(uID);
        for (Items list:itemsList) {
            logger.info("��Ʒ������Ϊ��{}",list.getType());
            items =itemsDao.getItemsByCart(list.getType());
            itemsSet.addAll(items);
        }
        return itemsSet;
    }

    public List<Items> getAllItems() {
        return itemsDao.getAllItems();
    }

    public Pager<Items> getAllItems(Items items, int pageNum, int pageSize) {
        Pager<Items> itemsPager = new Pager<Items>(pageNum, pageSize, itemsDao.getAllItems());
        return itemsPager;
    }
}
