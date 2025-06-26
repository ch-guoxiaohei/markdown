package com.guoxiaohei.markdown.api;

import com.guoxiaohei.markdown.model.common.ResponseResult;
import com.guoxiaohei.markdown.model.vo.CategoryVo;
import com.guoxiaohei.markdown.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CategoryApi {

    private final Logger log = LoggerFactory.getLogger(CategoryApi.class);


    @Autowired
    private CategoryService categoryService;


    @GetMapping("category")
    public ResponseResult<List<CategoryVo>> category() {
        return new ResponseResult(HttpStatus.OK.value(), "find successful", categoryService.findAll());

    }

    @PostMapping("category")
    public ResponseResult<String> insertCategory(String name) {
        return new ResponseResult<>(HttpStatus.OK.value(), "save successful", categoryService.insertCategory(name));
    }
}
