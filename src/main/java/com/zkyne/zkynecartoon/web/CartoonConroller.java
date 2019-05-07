package com.zkyne.zkynecartoon.web;

import com.zkyne.zkynecartoon.entity.Cartoon;
import com.zkyne.zkynecartoon.entity.Category;
import com.zkyne.zkynecartoon.service.ICartoonService;
import com.zkyne.zkynecartoon.service.ICategoryService;
import com.zkyne.zkynecartoon.utils.AjaxResultUtils;
import com.zkyne.zkynecartoon.utils.CartoonUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CartoonConroller
 * @Description:
 * @Author: zhangkunjsww
 * @Date: 2019/4/18 11:05
 */
@Controller
@RequestMapping("cartoon")
@Slf4j
public class CartoonConroller {
    @Resource
    private ICartoonService cartoonService;
    @Resource
    private ICategoryService categoryService;

    @RequestMapping("/initAdd")
    public String initAdd(Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        return "cartoonAdd";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map<String, Object> add(String title, String author, Long categoryId, Integer type, Integer finishStatus,
                                   @RequestParam(name = "priceUnitChapter", required = false, defaultValue = "0") Integer priceUnitChapter,
                                   String brief, @RequestParam(name = "coverPic") MultipartFile coverFile) {
        if (StringUtils.isBlank(title)) {
            return AjaxResultUtils.error("作品名称为空");
        }
        if (StringUtils.isBlank(author)) {
            return AjaxResultUtils.error("作者笔名为空");
        }
        if (categoryId == null) {
            return AjaxResultUtils.error("作品分类有误");
        }
        if (type == null) {
            return AjaxResultUtils.error("漫画类型有误");
        }
        if (type != 0 && type != 1) {
            return AjaxResultUtils.error("漫画类型有误");
        }
        if (finishStatus == null) {
            return AjaxResultUtils.error("更新状态有误");
        }
        if (finishStatus != 0 && finishStatus != 1) {
            return AjaxResultUtils.error("更新状态有误");
        }
        if (StringUtils.isBlank(brief)) {
            return AjaxResultUtils.error("作品介绍为空");
        }
        if (coverFile == null || coverFile.isEmpty()) {
            return AjaxResultUtils.error("请选择作品封面");
        }
        String coverPic;
        try {
            coverPic = CartoonUploadUtils.uploadCartoonCover(coverFile);
        } catch (Exception e) {
            log.error("漫画作品封面上传->>上传失败:error:{}", e);
            return AjaxResultUtils.error(e.getMessage());
        }
        Cartoon cartoon = new Cartoon();
        cartoon.setTitle(title);
        cartoon.setAuthor(author);
        cartoon.setCategoryId(categoryId);
        cartoon.setType(type);
        cartoon.setFinishStatus(finishStatus);
        cartoon.setPriceUnitChapter(priceUnitChapter);
        cartoon.setBrief(brief);
        cartoon.setCoverPic(coverPic);
        try{
            cartoonService.addCartoon(cartoon);
            if (cartoon.getCartoonId() != null) {
                return AjaxResultUtils.success(cartoon.getCartoonId());
            }
            return AjaxResultUtils.error("作品创建失败");
        }catch (Exception e){
            log.error("漫画作品创建->>创建失败:error:{}", e);
            return AjaxResultUtils.error("作品创建失败");
        }
    }
}
