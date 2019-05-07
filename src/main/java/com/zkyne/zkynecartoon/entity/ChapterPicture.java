package com.zkyne.zkynecartoon.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: ChapterPicture
 * @Description:
 * @Author: zhangkunjsww
 * @Date: 2019/4/18 12:00
 */
@Entity
@Table(name = "chapter_picture")
public class ChapterPicture implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long pictureId;
    @Column(length = 200)
    private String picture;
    @Column
    private Integer picIndex;
    @Column
    private Long chapterId;
    @Column
    private Long cartoonId;
    @Column
    private Date createDate;
    @Column
    private Date modifyDate;

    public ChapterPicture() {
    }

    public ChapterPicture(String picture, Integer picIndex) {
        this.picture = picture;
        this.picIndex = picIndex;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getPicIndex() {
        return picIndex;
    }

    public void setPicIndex(Integer picIndex) {
        this.picIndex = picIndex;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public Long getCartoonId() {
        return cartoonId;
    }

    public void setCartoonId(Long cartoonId) {
        this.cartoonId = cartoonId;
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
