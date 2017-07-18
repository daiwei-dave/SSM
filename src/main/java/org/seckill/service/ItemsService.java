package org.seckill.service;

import org.seckill.entity.Items;
import org.seckill.entity.Pager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Created by daiwei on 2017/5/14.
 */
public interface ItemsService {
    /**
     * 通过用户的购物车获取关联信息
     * @return
     */
    Set<Items> getItemsByCart(long uID);

    List<Items> getAllItems();

    /**
     * 分页查询
     * @param items
     * @param pageNum 当前页
     * @param pageSize 当前页条数
     * @return
     */
    Pager<Items> getAllItems(Items items,int pageNum, int pageSize);
}
