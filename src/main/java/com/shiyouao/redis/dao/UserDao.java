package com.shiyouao.redis.dao;


import com.shiyouao.redis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author sya
 * @Date 2019/1/18
 */
@Transactional
public interface UserDao extends PagingAndSortingRepository<User, Long>,JpaSpecificationExecutor<User>, JpaRepository<User,Long>{

    User findById(String id);
}
