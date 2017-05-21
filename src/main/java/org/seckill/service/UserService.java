package org.seckill.service;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Items;
import org.seckill.entity.User;

import java.util.List;

/**
 * Created by daiwei on 2017/5/14.
 */
public interface UserService {


    /**
     * 检查用户
     */
    User checkUser(String name,String password);





}
