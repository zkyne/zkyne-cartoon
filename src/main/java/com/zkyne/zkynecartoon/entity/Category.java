package com.zkyne.zkynecartoon.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: Category
 * @Description:
 * @Author: zhangkunjsww
 * @Date: 2019/4/19 15:53
 */
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long categoryId;
    @Column(length = 32)
    private String title;
    @Column
    private Date createDate;
    @Column
    private Date modifyDate;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
