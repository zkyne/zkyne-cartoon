package com.zkyne.zkynecartoon.utils;

import com.google.common.collect.Lists;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: CartoonUploadUtils
 * @Description:
 * @Author: zhangkunjsww
 * @Date: 2019/4/22 11:59
 */
public class CartoonUploadUtils {
    private static final List<String> EXT_ALLOW = Lists.newArrayList(".jpg",".png");

    public static String uploadCartoonCover(MultipartFile coverFile) throws Exception {
        String filePath = "D://data//static//zkyne_cartoon/";
        String fileName = upload(filePath, coverFile);
        return "/zkyne_cartoon/" + fileName;
    }

    public static String uploadChapterPicture(Long cartoonId, MultipartFile coverFile) throws Exception {
        if(cartoonId == null){
            throw new Exception("漫画作品有误");
        }
        // 上传后的路径
        String filePath = "D://data//static//zkyne_cartoon//" + cartoonId + "/";
        String fileName = upload(filePath, coverFile);
        return "/zkyne_cartoon/" + cartoonId + "/" + fileName;
    }

    private static String upload(String parentPath, MultipartFile coverFile) throws Exception {
        // 文件名
        String fileName = coverFile.getOriginalFilename();
        if (fileName == null) {
            throw new Exception("上传文件有误");
        }
        // 后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        if(!EXT_ALLOW.contains(suffixName)){
            throw new Exception("上传图片格式有误");
        }
        // 新文件名
        fileName = UUID.randomUUID() + suffixName;
        File dest = new File(parentPath + fileName);
        if (!dest.getParentFile().exists()) {
            boolean mkdirs = dest.getParentFile().mkdirs();
            if (!mkdirs) {
                throw new Exception("封面上传失败");
            }
        }
        try {
            coverFile.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("封面上传失败");
        }
        return fileName;
    }

}
