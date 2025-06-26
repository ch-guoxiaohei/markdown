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

    @Autowired
    private CategoryRepository categoryRepository;


    public String insertCategory(String name) {
        String uuid = UuIdUtil.uuid();
        Date now = new Date();
        Category category = new Category(uuid, name, now, now);
        categoryRepository.saveAndFlush(category);
        return uuid;
    }

    public List<CategoryVo> findAll() {
        return categoryRepository.findAll().stream().map(u -> new CategoryVo(u.getId(), u.getName())).collect(Collectors.toList());
    }
}
