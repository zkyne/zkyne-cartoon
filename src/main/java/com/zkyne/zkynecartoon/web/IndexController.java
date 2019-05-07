package com.zkyne.zkynecartoon.web;

import com.zkyne.zkynecartoon.entity.Cartoon;
import com.zkyne.zkynecartoon.service.ICartoonService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @ClassName: IndexController
 * @Description:
 * @Author: zhangkunjsww
 * @Date: 2019/4/18 11:05
 */
@Controller
@RequestMapping("")
public class IndexController {
    @Resource
    private ICartoonService cartoonService;

    @RequestMapping("")
    public String index(@RequestParam(name = "pageNo",defaultValue = "1")Integer pageNo, Model model) {
        Page<Cartoon> page = cartoonService.findByPage(pageNo, 10);
        model.addAttribute("page", page);
        return "cartoons";
    }

    @RequestMapping("upload")
    public String upload() {
        return "upload";
    }
}
