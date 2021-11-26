package com.guoxiaohei.markdown.service;

import com.guoxiaohei.markdown.dao.ArticleRepository;
import com.guoxiaohei.markdown.model.projo.Article;
import com.guoxiaohei.markdown.utils.UuIdUtil;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ArticleService {

  private ArticleRepository articleRepository;

  public ArticleService(@Autowired ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }


  @Transactional(rollbackFor = Exception.class)
  public String insertArticle(Article article) {
    String id = UuIdUtil.uuid();
    article.setId(id);
    article.setCreateTime(new Date());
    article.setUpdateTime(article.getCreateTime());
    articleRepository.saveAndFlush(article);
    return id;
  }

  @Transactional(rollbackFor = Exception.class)
  public String updateArticle(Article article){
    article.setUpdateTime(new Date());
    articleRepository.saveAndFlush(article);
    return article.getId();
  }

  public List<Article> selectAll() {
    return articleRepository.findAll();
  }

  public Article findById(String id){
    return articleRepository.findById(id).orElseGet(Article::new);
  }

}
