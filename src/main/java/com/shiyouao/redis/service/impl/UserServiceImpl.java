package com.shiyouao.redis.service.impl;

import com.shiyouao.redis.dao.UserDao;
import com.shiyouao.redis.entity.User;
import com.shiyouao.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author sya
 * @Date 2019/1/18
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public void detele(User user) {
        userDao.delete(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
