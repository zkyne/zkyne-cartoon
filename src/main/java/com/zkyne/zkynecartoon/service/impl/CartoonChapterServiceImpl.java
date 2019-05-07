package com.zkyne.zkynecartoon.service.impl;

import com.zkyne.zkynecartoon.entity.Cartoon;
import com.zkyne.zkynecartoon.entity.CartoonChapter;
import com.zkyne.zkynecartoon.entity.ChapterPicture;
import com.zkyne.zkynecartoon.repository.CartoonChapterRepository;
import com.zkyne.zkynecartoon.repository.CartoonRepository;
import com.zkyne.zkynecartoon.repository.ChapterPictureRepository;
import com.zkyne.zkynecartoon.service.ICartoonChapterService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName: CartoonChapterServiceImpl
 * @Description:
 * @Author: zhangkunjsww
 * @Date: 2019/4/18 11:15
 */
@Service
public class CartoonChapterServiceImpl implements ICartoonChapterService {
    @Resource
    private CartoonRepository cartoonRepository;
    @Resource
    private CartoonChapterRepository cartoonChapterRepository;
    @Resource
    private ChapterPictureRepository chapterPictureRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addChapter(CartoonChapter cartoonChapter) throws Exception {
        List<ChapterPicture> chapterPictures = cartoonChapter.getChapterPictures();
        Long cartoonId = cartoonChapter.getCartoonId();
        // 保存章节
        Integer maxChapterIndex = cartoonChapterRepository.findMaxChapterIndex(cartoonId);
        if(maxChapterIndex == null){
            maxChapterIndex = 0;
        }
        cartoonChapter.setChapterIndex(maxChapterIndex + 1);
        cartoonChapter.setIsDelete(0);
        cartoonChapter.setPictureCnt(chapterPictures == null? 0:chapterPictures.size());
        cartoonChapter.setCreateDate(new Date());
        cartoonChapter.setModifyDate(new Date());
        cartoonChapterRepository.save(cartoonChapter);
        Long chapterId = cartoonChapter.getChapterId();
        // 更新作品
        Cartoon cartoon = cartoonRepository.getOne(cartoonId);
        cartoon.setChapterCnt(cartoon.getChapterCnt() +1);
        cartoon.setChapterUpdateTimestamp(System.currentTimeMillis());
        cartoon.setCartoonUpdateTimestamp(System.currentTimeMillis());
        cartoon.setModifyDate(new Date());
        cartoonRepository.saveAndFlush(cartoon);
        //保存章节内容
        if(chapterPictures != null && chapterPictures.size() > 0){
            for (ChapterPicture chapterPicture : chapterPictures) {
                chapterPicture.setCartoonId(cartoonId);
                chapterPicture.setChapterId(chapterId);
                chapterPicture.setCreateDate(new Date());
                chapterPicture.setModifyDate(new Date());
            }
            chapterPictureRepository.saveAll(chapterPictures);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyChapter(CartoonChapter cartoonChapter) throws Exception {
        List<ChapterPicture> chapterPictures = cartoonChapter.getChapterPictures();
        // 更新章节信息
        cartoonChapter.setPictureCnt(chapterPictures == null? 0:chapterPictures.size());
        cartoonChapter.setCreateDate(new Date());
        cartoonChapter.setModifyDate(new Date());
        cartoonChapterRepository.saveAndFlush(cartoonChapter);
        // 更新章节内容,先删除原有内容
        chapterPictureRepository.deleteByChapterId(cartoonChapter.getChapterId());
        //保存章节内容
        if(chapterPictures != null && chapterPictures.size() > 0){
            for (ChapterPicture chapterPicture : chapterPictures) {
                chapterPicture.setCartoonId(cartoonChapter.getCartoonId());
                chapterPicture.setChapterId(cartoonChapter.getChapterId());
                chapterPicture.setCreateDate(new Date());
                chapterPicture.setModifyDate(new Date());
            }
            chapterPictureRepository.saveAll(chapterPictures);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyDeleteStatus(Long chapterId, Integer isDelete) throws Exception {
        // 更新章节
        CartoonChapter cartoonChapter = cartoonChapterRepository.getOne(chapterId);
        if(cartoonChapter == null){
            throw new Exception("章节不存在");
        }
        if(isDelete.equals(cartoonChapter.getIsDelete())){
            return;
        }
        cartoonChapter.setIsDelete(isDelete);
        cartoonChapter.setModifyDate(new Date());
        cartoonChapterRepository.saveAndFlush(cartoonChapter);
        // 更新作品
        Cartoon cartoon = cartoonRepository.getOne(cartoonChapter.getCartoonId());
        if(isDelete == 0){
            // 恢复操作
            cartoon.setChapterCnt(cartoon.getChapterCnt() + 1);
        }else{
            // 删除操作
            cartoon.setChapterCnt(cartoon.getChapterCnt() - 1);
        }
        cartoon.setChapterUpdateTimestamp(System.currentTimeMillis());
        cartoon.setCartoonUpdateTimestamp(System.currentTimeMillis());
        cartoon.setModifyDate(new Date());
        cartoonRepository.saveAndFlush(cartoon);
    }

    @Override
    public Page<CartoonChapter> findByPage(Long cartoonId, int pageNo, int pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC, "chapterIndex");
        pageNo = (pageNo - 1) < 0 ? 0:pageNo - 1;
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return cartoonChapterRepository.findByCartoonId(cartoonId, pageable);
//        CartoonChapter cartoonChapter = new CartoonChapter();
//        cartoonChapter.setCartoonId(cartoonId);
//        Example<CartoonChapter> chapterExample = Example.of(cartoonChapter);
//        return cartoonChapterRepository.findAll(chapterExample, pageable);
    }

    @Override
    public CartoonChapter findByChapterId(Long chapterId, boolean withPictrue) {
        CartoonChapter cartoonChapter =  cartoonChapterRepository.getOne(chapterId);
        if(withPictrue){
            Sort sort = new Sort(Sort.Direction.ASC, "picIndex");
            List<ChapterPicture> chapterPictures = chapterPictureRepository.findAllByChapterId(chapterId, sort);
            cartoonChapter.setChapterPictures(chapterPictures);
        }
        return cartoonChapter;
    }
}
