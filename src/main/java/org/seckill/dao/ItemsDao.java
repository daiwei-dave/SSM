package org.seckill.dao;

import org.seckill.entity.Items;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/5/14.
 */
@Component
public interface ItemsDao {
    Set<Items> getItemsByCart(String name);

    Items findItemsById(long itemsID);
    List<Items> getAllItems();
}
