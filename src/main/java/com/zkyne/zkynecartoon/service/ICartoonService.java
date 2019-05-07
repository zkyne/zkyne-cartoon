package com.zkyne.zkynecartoon.service;

import com.zkyne.zkynecartoon.entity.Cartoon;
import org.springframework.data.domain.Page;

/**
 * @ClassName: ICartoonService
 * @Description:
 * @Author: zhangkunjsww
 * @Date: 2019/4/18 11:14
 */
public interface ICartoonService {
    /**
     * 创建漫画
     * @param cartoon
     * @throws Exception
     */
    void addCartoon(Cartoon cartoon) throws Exception;

    /**
     * 分页查询我的漫画作品
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Cartoon> findByPage(int pageNo, int pageSize);

    /**
     * 通过主键查询
     * @param cartoonId
     * @return
     */
    Cartoon findByCartoonId(Long cartoonId);
}
