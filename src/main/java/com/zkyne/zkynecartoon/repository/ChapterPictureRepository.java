package com.zkyne.zkynecartoon.repository;

import com.zkyne.zkynecartoon.entity.ChapterPicture;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName: ChapterPictureRepository
 * @Description:
 * @Author: zhangkunjsww
 * @Date: 2019/4/18 12:37
 */
public interface ChapterPictureRepository extends JpaRepository<ChapterPicture,Long> {
    /**
     * 查询章节下所有图片
     * @param chapterId
     * @param sort
     * @return
     */
    List<ChapterPicture> findAllByChapterId(Long chapterId, Sort sort);

    /**
     * 删除章节内容
     * @param chapterId
     */
    void deleteByChapterId(Long chapterId);
}
