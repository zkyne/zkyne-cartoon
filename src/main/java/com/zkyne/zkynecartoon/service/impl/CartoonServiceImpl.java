package com.zkyne.zkynecartoon.service.impl;

import com.zkyne.zkynecartoon.entity.Cartoon;
import com.zkyne.zkynecartoon.repository.CartoonRepository;
import com.zkyne.zkynecartoon.service.ICartoonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName: CartoonServiceImpl
 * @Description:
 * @Author: zhangkunjsww
 * @Date: 2019/4/18 11:15
 */
@Service
public class CartoonServiceImpl implements ICartoonService {

    @Resource
    private CartoonRepository cartoonRepository;

    @Override
    public void addCartoon(Cartoon cartoon) throws Exception {
        cartoon.setIsFree(1);
        cartoon.setIsDelete(0);
        cartoon.setWholePrice(0);
        cartoon.setShelfStatus(0);
        cartoon.setCartoonUpdateTimestamp(System.currentTimeMillis());
        cartoon.setChapterUpdateTimestamp(System.currentTimeMillis());
        cartoon.setChapterCnt(0);
        cartoon.setCreateDate(new Date());
        cartoon.setModifyDate(new Date());
        cartoonRepository.save(cartoon);
    }

    @Override
    public Page<Cartoon> findByPage(int pageNo, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "cartoonId");
        pageNo = (pageNo - 1) < 0 ? 0:pageNo - 1;
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return cartoonRepository.findAll(pageable);
    }

    @Override
    public Cartoon findByCartoonId(Long cartoonId) {
        return cartoonRepository.getOne(cartoonId);
    }
}
