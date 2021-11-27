package com.guoxiaohei.markdown.service;

import com.guoxiaohei.markdown.dao.CategoryRepository;
import com.guoxiaohei.markdown.model.projo.Category;
import com.guoxiaohei.markdown.model.vo.CategoryVo;
import com.guoxiaohei.markdown.utils.UuIdUtil;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryService {

  private CategoryRepository categoryRepository;

  public CategoryService(@Autowired CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public String insertCategory(String name) {
    String uuid = UuIdUtil.uuid();
    Date now = new Date();
    Category category = Category.builder().id(uuid).createTime(now).updateTime(now).name(name)
        .build();
    categoryRepository.saveAndFlush(category);
    return uuid;
  }


  public List<CategoryVo> findAll() {
    return categoryRepository.findAll().stream()
        .map(u -> CategoryVo.builder().id(u.getId()).name(u.getName()).build())
        .collect(Collectors.toList());
  }
}
