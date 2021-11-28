package com.guoxiaohei.markdown.service;

import com.guoxiaohei.markdown.dao.ArticleRepository;
import com.guoxiaohei.markdown.model.projo.Article;
import com.guoxiaohei.markdown.utils.UuIdUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
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
  public String updateArticle(Article article) {
    article.setUpdateTime(new Date());
    articleRepository.saveAndFlush(article);
    return article.getId();
  }


  public Article findById(String id) {
    return articleRepository.findById(id).orElseGet(Article::new);
  }

  public String deleteById(String id) {
    Article exist = findById(id);
    if (Objects.nonNull(exist)) {
      articleRepository.deleteById(id);
      return exist.getId();
    }
    return StringUtils.EMPTY;
  }

  public Page pageArticle(String category, String search, int pageNum, int pageSize) {
    log.info("search category : {}", category);
    log.info("like key : {}", search);
    category = "default".equals(category) ? "" : category;
    Specification<Article> specification = buildQueryCondition(category, search);
    Pageable pageable = PageRequest
        .of(pageNum, pageSize, Sort.by(Direction.DESC, "createTime"));
    return articleRepository.findAll(specification, pageable);
  }

  private Specification<Article> buildQueryCondition(String category, String search) {
    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();
      List<Predicate> orLikeQuery = new ArrayList<>();
      if (StringUtils.isNotBlank(search)) {
        orLikeQuery.add(criteriaBuilder.like(root.get("title"), "%" + search + "%"));
        orLikeQuery.add(criteriaBuilder.like(root.get("content"), "%" + search + "%"));
        orLikeQuery.add(criteriaBuilder.like(root.get("overview"), "%" + search + "%"));
        predicates.add(criteriaBuilder.or(orLikeQuery.toArray(new Predicate[0])));
      }
      if (StringUtils.isNotBlank(category)) {
        predicates.add(criteriaBuilder.equal(root.get("categoryId"), category));
      }
      int size = predicates.size();
      return criteriaBuilder.and(predicates.toArray(new Predicate[size]));
    };
  }

}
