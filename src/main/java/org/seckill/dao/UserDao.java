package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Cart;
import org.seckill.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by daiwei on 2017/5/14.
 */
@Component
public interface UserDao {
    //添加进购物车
    User checkUser(@Param("name") String name, @Param("password") String password);
}
