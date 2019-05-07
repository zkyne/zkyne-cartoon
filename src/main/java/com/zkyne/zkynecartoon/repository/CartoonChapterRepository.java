package com.zkyne.zkynecartoon.repository;

import com.zkyne.zkynecartoon.entity.CartoonChapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @ClassName: CartoonChapterRepository
 * @Description:
 * @Author: zhangkunjsww
 * @Date: 2019/4/18 12:36
 */
public interface CartoonChapterRepository extends JpaRepository<CartoonChapter,Long> {
    /**
     * 查询章节最大索引
     * @param cartoonId
     * @return
     */
    @Query("SELECT MAX(cc.chapterIndex) FROM CartoonChapter cc WHERE cc.cartoonId = :cartoonId")
    Integer findMaxChapterIndex(@Param("cartoonId") Long cartoonId);

    /**
     * 分页查询章节列表
     * @param cartoonId
     * @param pageable
     * @return
     */
    Page<CartoonChapter> findByCartoonId(Long cartoonId, Pageable pageable);
}
