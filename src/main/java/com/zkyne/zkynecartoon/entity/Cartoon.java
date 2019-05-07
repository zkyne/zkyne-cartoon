package com.zkyne.zkynecartoon.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: Cartoon
 * @Description:
 * @Author: zhangkunjsww
 * @Date: 2019/4/18 11:58
 */
@Entity
@Table(name = "cartoon")
public class Cartoon {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long cartoonId;
    @Column(length = 64, nullable = false)
    private String title;
    @Column(length = 128)
    private String origin;
    @Column(length = 128)
    private String originCartoonId;
    @Column(length = 300)
    private String brief;
    @Column
    private Integer isFree;
    @Column
    private Integer wholePrice;
    @Column
    private Integer priceUnitChapter;
    @Column(length = 200)
    private String keyWords;
    @Column(length = 200)
    private String coverPic;
    @Column
    private Integer type;
    @Column
    private Long authorId;
    @Column(length = 32)
    private String author;
    @Column
    private Integer finishStatus;
    @Column
    private Integer shelfStatus;
    @Column
    private Integer chapterCnt;
    @Column
    private Integer isDelete;
    @Column
    private Long lastChapterId;
    @Column(length = 64)
    private String lastChapterTitle;
    @Column
    private Long cartoonUpdateTimestamp;
    @Column
    private Long chapterUpdateTimestamp;
    @Column
    private Long categoryId;
    @Column
    private Date createDate;
    @Column
    private Date modifyDate;

    public Long getCartoonId() {
        return cartoonId;
    }

    public void setCartoonId(Long cartoonId) {
        this.cartoonId = cartoonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginCartoonId() {
        return originCartoonId;
    }

    public void setOriginCartoonId(String originCartoonId) {
        this.originCartoonId = originCartoonId;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public Integer getWholePrice() {
        return wholePrice;
    }

    public void setWholePrice(Integer wholePrice) {
        this.wholePrice = wholePrice;
    }

    public Integer getPriceUnitChapter() {
        return priceUnitChapter;
    }

    public void setPriceUnitChapter(Integer priceUnitChapter) {
        this.priceUnitChapter = priceUnitChapter;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(Integer finishStatus) {
        this.finishStatus = finishStatus;
    }

    public Integer getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(Integer shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    public Integer getChapterCnt() {
        return chapterCnt;
    }

    public void setChapterCnt(Integer chapterCnt) {
        this.chapterCnt = chapterCnt;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Long getLastChapterId() {
        return lastChapterId;
    }

    public void setLastChapterId(Long lastChapterId) {
        this.lastChapterId = lastChapterId;
    }

    public String getLastChapterTitle() {
        return lastChapterTitle;
    }

    public void setLastChapterTitle(String lastChapterTitle) {
        this.lastChapterTitle = lastChapterTitle;
    }

    public Long getCartoonUpdateTimestamp() {
        return cartoonUpdateTimestamp;
    }

    public void setCartoonUpdateTimestamp(Long cartoonUpdateTimestamp) {
        this.cartoonUpdateTimestamp = cartoonUpdateTimestamp;
    }

    public Long getChapterUpdateTimestamp() {
        return chapterUpdateTimestamp;
    }

    public void setChapterUpdateTimestamp(Long chapterUpdateTimestamp) {
        this.chapterUpdateTimestamp = chapterUpdateTimestamp;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
