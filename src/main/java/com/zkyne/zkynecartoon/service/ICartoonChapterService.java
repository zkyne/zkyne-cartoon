package com.zkyne.zkynecartoon.service;

import com.zkyne.zkynecartoon.entity.CartoonChapter;
import org.springframework.data.domain.Page;

/**
 * @ClassName: ICartoonChapterService
 * @Description:
 * @Author: zhangkunjsww
 * @Date: 2019/4/18 11:14
 */
public interface ICartoonChapterService {
    /**
     * 漫画章节创建
     * @param cartoonChapter
     * @throws Exception
     */
    void addChapter(CartoonChapter cartoonChapter) throws Exception;

    /**
     * 章节更新
     * @param cartoonChapter
     * @throws Exception
     */
    void modifyChapter(CartoonChapter cartoonChapter) throws Exception;

    /**
     * 漫画章节更新
     * @param chapterId
     * @param isDelete
     * @throws Exception
     */
    void modifyDeleteStatus(Long chapterId, Integer isDelete) throws Exception;

    /**
     * 分页查询章节列表
     * @param cartoonId
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<CartoonChapter> findByPage(Long cartoonId, int pageNo, int pageSize);

    /**
     * 通过chapterId查询
     * @param chapterId
     * @param withPicture
     * @return
     */
    CartoonChapter findByChapterId(Long chapterId, boolean withPicture);


}
