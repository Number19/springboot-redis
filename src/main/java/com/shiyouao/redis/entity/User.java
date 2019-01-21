package com.shiyouao.redis.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author sya
 * @Date 2019/1/18
 */
@Entity
@Table(name = "user")
@Data
public class User extends BaseEntity {

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "age")
    private int age;
}
