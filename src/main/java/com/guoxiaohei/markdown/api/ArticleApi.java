package com.guoxiaohei.markdown.api;

import com.guoxiaohei.markdown.model.common.ResponseResult;
import com.guoxiaohei.markdown.model.projo.Article;
import com.guoxiaohei.markdown.service.ArticleService;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang.StringUtils;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class ArticleApi {

    @Autowired
    private ArticleService articleService;


    @PostMapping(value = "article", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResult<String> insertArticle(@RequestBody Article article) {
        return new ResponseResult<>(HttpStatus.OK.value(), articleService.insertArticle(article));
    }

    @PostMapping(value = "article/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResult<String> updateArticle(@PathVariable("id") String id, @RequestBody Article article) {
        if (!StringUtils.equals(id, article.getId())) {
            return new ResponseResult<>(HttpStatus.BAD_REQUEST.value(), "valid params");
        }
        return new ResponseResult<>(HttpStatus.OK.value(), articleService.updateArticle(article));
    }

    @GetMapping(value = "article", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResult<Page> page(@RequestParam("categoryId") String categoryId, @RequestParam(value = "search") String search, @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        String decode = URLDecoder.decode(search, StandardCharsets.UTF_8);
        return new ResponseResult(HttpStatus.OK.value(), "success", articleService.pageArticle(categoryId, decode, page, pageSize));
    }

    @GetMapping(value = "article/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResult<Article> findById(@PathVariable("id") String id) {
        return new ResponseResult<>(HttpStatus.OK.value(), "success", articleService.findById(id));
    }

    @GetMapping(value = "articleDel/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResult<String> deleteById(@PathVariable("id") String id) {
        return new ResponseResult<>(HttpStatus.OK.value(), "success", articleService.deleteById(id));
    }

}
