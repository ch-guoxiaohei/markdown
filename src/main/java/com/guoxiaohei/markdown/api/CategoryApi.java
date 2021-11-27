package com.guoxiaohei.markdown.api;

import com.guoxiaohei.markdown.model.common.ResponseResult;
import com.guoxiaohei.markdown.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class CategoryApi {

  private CategoryService categoryService;

  public CategoryApi(@Autowired CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("category")
  public ResponseResult category() {
    return ResponseResult.builder().code(HttpStatus.OK.value()).message("find successful")
        .data(categoryService.findAll()).build();
  }

  @PostMapping("category")
  public ResponseResult insertCategory(String name){
    return ResponseResult.builder().code(HttpStatus.OK.value()).message("save successful")
        .data(categoryService.insertCategory(name)).build();
  }
}
