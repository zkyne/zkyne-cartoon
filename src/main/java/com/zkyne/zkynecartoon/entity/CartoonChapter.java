package com.zkyne.zkynecartoon.entity;

import com.google.common.collect.Lists;
import com.zkyne.zkynecartoon.utils.AjaxResultUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: CartoonChapter
 * @Description:
 * @Author: zhangkunjsww
 * @Date: 2019/4/18 11:58
 */
@Entity
@Table(name = "cartoon_chapter")
public class CartoonChapter implements Serializable {

    private static final long serialVersionUID = 1686292950766388748L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long chapterId;
    @Column(length = 64, nullable = false)
    private String title;
    @Column(length = 200)
    private String coverPic;
    @Column
    private Integer isFree;
    @Column
    private Long cartoonId;
    @Column
    private Integer pictureCnt;
    @Column
    private Integer chapterIndex;
    @Column
    private Integer isDelete;
    @Column
    private Date createDate;
    @Column
    private Date modifyDate;
    @Transient
    private List<ChapterPicture> chapterPictures;

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public Long getCartoonId() {
        return cartoonId;
    }

    public void setCartoonId(Long cartoonId) {
        this.cartoonId = cartoonId;
    }

    public Integer getPictureCnt() {
        return pictureCnt;
    }

    public void setPictureCnt(Integer pictureCnt) {
        this.pictureCnt = pictureCnt;
    }

    public Integer getChapterIndex() {
        return chapterIndex;
    }

    public void setChapterIndex(Integer chapterIndex) {
        this.chapterIndex = chapterIndex;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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

    public List<ChapterPicture> getChapterPictures() {
        return chapterPictures;
    }

    public String getChapterPicturesStr() {
        StringBuilder builder = new StringBuilder();
        if(this.chapterPictures != null && this.chapterPictures.size() > 0){
            for (ChapterPicture chapterPicture : chapterPictures) {
                builder.append(chapterPicture.getPicture()).append(",");
            }
        }
        if(builder.length() > 0){
            return builder.toString().substring(0,builder.length()-1);
        }
        return "";
    }

    public void setChapterPictures(String chapterPictures) throws Exception {
        if (StringUtils.isNotBlank(chapterPictures)) {
            String[] split;
            try {
                split = chapterPictures.split(",");
            } catch (Exception e) {
                throw new Exception("章节内容有误");
            }
            if(split.length > 0){
                this.chapterPictures = Lists.newArrayList();
                int length = split.length;
                for(int i = 0;i < length; i++){
                    this.chapterPictures.add(new ChapterPicture(split[i],i));
                }
            }
        }
    }

    public void setChapterPictures(List<ChapterPicture> chapterPictures) {
       this.chapterPictures = chapterPictures;
    }
}
