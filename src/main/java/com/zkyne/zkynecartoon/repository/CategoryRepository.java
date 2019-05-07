package com.zkyne.zkynecartoon.repository;

import com.zkyne.zkynecartoon.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: CategoryRepository
 * @Description:
 * @Author: zhangkunjsww
 * @Date: 2019/4/19 15:56
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
