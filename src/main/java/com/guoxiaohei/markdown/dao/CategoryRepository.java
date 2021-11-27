package com.guoxiaohei.markdown.dao;

import com.guoxiaohei.markdown.model.projo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {

}
