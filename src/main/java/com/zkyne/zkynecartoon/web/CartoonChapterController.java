package com.zkyne.zkynecartoon.web;

import com.zkyne.zkynecartoon.entity.Cartoon;
import com.zkyne.zkynecartoon.entity.CartoonChapter;
import com.zkyne.zkynecartoon.service.ICartoonChapterService;
import com.zkyne.zkynecartoon.service.ICartoonService;
import com.zkyne.zkynecartoon.utils.AjaxResultUtils;
import com.zkyne.zkynecartoon.utils.CartoonUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName: CartoonChapterController
 * @Description:
 * @Author: zhangkunjsww
 * @Date: 2019/4/18 11:07
 */
@Controller
@RequestMapping("cartoons")
@Slf4j
public class CartoonChapterController {
    @Resource
    private ICartoonService cartoonService;
    @Resource
    private ICartoonChapterService cartoonChapterService;

    /**
     * 章节管理
     *
     * @param cartoonId
     * @param pageNo
     * @param model
     * @return
     */
    @GetMapping("/{cartoonId}/chapters")
    public String listChapters(@PathVariable(name = "cartoonId") Long cartoonId, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, Model model) {
        Cartoon cartoon = cartoonService.findByCartoonId(cartoonId);
        model.addAttribute("cartoon", cartoon);
        Page<CartoonChapter> page = cartoonChapterService.findByPage(cartoonId, pageNo, 10);
        model.addAttribute("page", page);
        return "chapters";
    }

    /**
     * 初始化添加章节页面
     *
     * @param cartoonId
     * @param model
     * @return
     */
    @GetMapping("/{cartoonId}/chapters/init")
    public String initAdd(@PathVariable(name = "cartoonId") Long cartoonId, Model model) {
        Cartoon cartoon = cartoonService.findByCartoonId(cartoonId);
        model.addAttribute("cartoon", cartoon);
        return "chapterAdd";
    }

    /**
     * 初始化添加修改页面
     *
     * @param chapterId
     * @param model
     * @return
     */
    @GetMapping("/{cartoonId}/chapters/{chapterId}/init")
    public String initModify(@PathVariable(name = "cartoonId") Long cartoonId, @PathVariable(name = "chapterId") Long chapterId, Model model) {
        CartoonChapter cartoonChapter = cartoonChapterService.findByChapterId(chapterId, true);
        if (cartoonChapter != null) {
            Cartoon cartoon = cartoonService.findByCartoonId(cartoonChapter.getCartoonId());
            model.addAttribute("cartoon", cartoon);
        }
        model.addAttribute("cartoonChapter", cartoonChapter);
        return "chapterModify";
    }

    /**
     * 添加章节
     *
     * @param cartoonId
     * @param title
     * @param isFree
     * @param pictures
     * @param coverFile
     * @return
     */
    @PostMapping("/{cartoonId}/chapters")
    @ResponseBody
    public Map<String, Object> add(@PathVariable(name = "cartoonId") Long cartoonId, String title, Integer isFree, String pictures, @RequestParam(name = "coverPic") MultipartFile coverFile) {
        if (cartoonId == null) {
            return AjaxResultUtils.error("参数有误");
        }
        if (StringUtils.isBlank(title)) {
            return AjaxResultUtils.error("章节标题为空");
        }
        if (isFree == null) {
            return AjaxResultUtils.error("收费状态有误");
        }
        if (isFree != 0 && isFree != 1) {
            return AjaxResultUtils.error("收费状态有误");
        }
        if (coverFile == null || coverFile.isEmpty()) {
            return AjaxResultUtils.error("请选择章节封面");
        }
        Cartoon cartoon = cartoonService.findByCartoonId(cartoonId);
        if (cartoon == null) {
            return AjaxResultUtils.error("参数有误");
        }
        String coverPic;
        try {
            coverPic = CartoonUploadUtils.uploadChapterPicture(cartoonId, coverFile);
        } catch (Exception e) {
            log.error("漫画章节封面上传->>漫画ID:{},上传失败:error:{}", cartoonId, e);
            return AjaxResultUtils.error(e.getMessage());
        }
        CartoonChapter cartoonChapter = new CartoonChapter();
        cartoonChapter.setCartoonId(cartoonId);
        cartoonChapter.setTitle(title);
        cartoonChapter.setCoverPic(coverPic);
        cartoonChapter.setIsFree(isFree);
        try {
            cartoonChapter.setChapterPictures(pictures);
        } catch (Exception e) {
            return AjaxResultUtils.error("章节内容有误");
        }
        try {
            cartoonChapterService.addChapter(cartoonChapter);
            return AjaxResultUtils.success(cartoonChapter);
        } catch (Exception e) {
            log.error("漫画章节创建->>漫画ID:{},创建失败:error:{}", cartoonId, e);
            return AjaxResultUtils.error("章节添加失败");
        }
    }

    @PostMapping("/{cartoonId}/chapters/{chapterId}")
    @ResponseBody
    public Map<String, Object> modify(@PathVariable("cartoonId") Long cartoonId, @PathVariable(name = "chapterId") Long chapterId, String title, Integer isFree, String pictures, @RequestParam(name = "coverPic") MultipartFile coverFile) {
        if (chapterId == null) {
            return AjaxResultUtils.error("参数有误");
        }
        if (StringUtils.isBlank(title)) {
            return AjaxResultUtils.error("章节标题为空");
        }
        if (isFree == null) {
            return AjaxResultUtils.error("收费状态有误");
        }
        if (isFree != 0 && isFree != 1) {
            return AjaxResultUtils.error("收费状态有误");
        }

        CartoonChapter cartoonChapter = cartoonChapterService.findByChapterId(chapterId, false);
        if (cartoonChapter == null) {
            return AjaxResultUtils.error("参数有误");
        }
        cartoonChapter.setTitle(title);
        cartoonChapter.setIsFree(isFree);
        if (coverFile != null && !coverFile.isEmpty()) {
            try {
                String coverPic = CartoonUploadUtils.uploadChapterPicture(cartoonChapter.getCartoonId(), coverFile);
                cartoonChapter.setCoverPic(coverPic);
            } catch (Exception e) {
                log.error("漫画章节封面上传->>漫画ID:{},上传失败:error:{}", cartoonChapter.getCartoonId(), e);
                return AjaxResultUtils.error(e.getMessage());
            }
        }
        try {
            cartoonChapter.setChapterPictures(pictures);
        } catch (Exception e) {
            return AjaxResultUtils.error("章节内容有误");
        }

        try {
            cartoonChapterService.modifyChapter(cartoonChapter);
            return AjaxResultUtils.success(cartoonChapter);
        } catch (Exception e) {
            log.error("漫画章节更新->>漫画章节ID:{},创建失败:error:{}", chapterId, e);
            return AjaxResultUtils.error("章节更新失败");
        }
    }

    /**
     * 上传章节内容
     *
     * @param cartoonId
     * @param pictureFile
     * @return
     */
    @PostMapping("/{cartoonId}/chapters/upload")
    @ResponseBody
    public Map<String, Object> upload(@PathVariable(name = "cartoonId") Long cartoonId, @RequestParam(name = "file") MultipartFile pictureFile) {
        if (cartoonId == null) {
            return AjaxResultUtils.error("漫画作品有误");
        }
        Cartoon byCartoonId = cartoonService.findByCartoonId(cartoonId);
        if (byCartoonId == null) {
            return AjaxResultUtils.error("漫画作品不存在");
        }
        if (pictureFile == null || pictureFile.isEmpty()) {
            return AjaxResultUtils.error("上传文件为空");
        }
        String pictureUrl;
        try {
            pictureUrl = CartoonUploadUtils.uploadChapterPicture(cartoonId, pictureFile);
        } catch (Exception e) {
            log.error("漫画章节内容上传->>漫画ID:{},上传失败:error:{}", cartoonId, e);
            return AjaxResultUtils.error(e.getMessage());
        }
        return AjaxResultUtils.success(pictureUrl);
    }

    /**
     * 显示/隐藏章节
     *
     * @param chapterId
     * @param isDelete
     * @return
     */
    @PatchMapping("/{cartoonId}/chapters/{chapterId}/{isDelete}")
    @ResponseBody
    public Map<String, Object> reverse(@PathVariable("cartoonId") Long cartoonId, @PathVariable("chapterId") Long chapterId, @PathVariable("isDelete") Integer isDelete) {
        if (chapterId == null || isDelete == null) {
            return AjaxResultUtils.error("参数有误.");
        }
        if (isDelete != 0 && isDelete != 1) {
            return AjaxResultUtils.error("参数有误.");
        }
        try {
            cartoonChapterService.modifyDeleteStatus(chapterId, isDelete);
            return AjaxResultUtils.success();
        } catch (Exception e) {
            log.error("漫画章节状态更新->>漫画章节ID:{},isDelete:{},更新失败:error:{}", chapterId, isDelete, e);
            return AjaxResultUtils.error(e.getMessage());
        }
    }

    /**
     * 预览章节
     *
     * @param chapterId
     * @return
     */
    @GetMapping("/{cartoonId}/chapters/{chapterId}")
    @ResponseBody
    public Map<String, Object> preview(@PathVariable("cartoonId") Long cartoonId, @PathVariable("chapterId") Long chapterId) {
        if (chapterId == null) {
            return AjaxResultUtils.error("参数有误.");
        }
        CartoonChapter cartoonChapter = cartoonChapterService.findByChapterId(chapterId, true);
        if (cartoonChapter != null) {
            return AjaxResultUtils.success(cartoonChapter);
        }
        return AjaxResultUtils.error("预览失败");
    }
}
