package org.seckill.service.impl;

import org.seckill.dao.UserDao;
import org.seckill.entity.User;
import org.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by daiwei on 2017/5/20.
 */
@Component
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    public User checkUser(String name, String password) {
        return userDao.checkUser(name, password);
    }
}
