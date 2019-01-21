package com.shiyouao.redis.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @Author sya
 * @Date 2019/1/18
 */
@MappedSuperclass
public class BaseEntity {
    @Id
    @GenericGenerator(name = "uuid",strategy = "uuid")
    protected String id;

    @Column(name = "create_date")
    Date createDate = new Date();

    @Column(name = "update_date")
    Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
