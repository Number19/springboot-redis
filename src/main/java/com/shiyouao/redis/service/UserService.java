package com.shiyouao.redis.service;

import com.shiyouao.redis.entity.User;

import java.util.List;

/**
 * @Author sya
 * @Date 2019/1/18
 */
public interface UserService {

    User save(User user);

    User findById(String id);

    void detele(User user);

    List<User> findAll();
}
