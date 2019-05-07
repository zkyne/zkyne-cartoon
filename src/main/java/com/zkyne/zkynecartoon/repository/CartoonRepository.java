package com.zkyne.zkynecartoon.repository;

import com.zkyne.zkynecartoon.entity.Cartoon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: CartoonRepository
 * @Description:
 * @Author: zhangkunjsww
 * @Date: 2019/4/18 12:34
 */
@Repository
public interface CartoonRepository extends JpaRepository<Cartoon,Long> {

}
