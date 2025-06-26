package com.guoxiaohei.markdown.service;

import com.guoxiaohei.markdown.dao.ArticleRepository;
import com.guoxiaohei.markdown.model.common.Constant;
import com.guoxiaohei.markdown.model.projo.Article;
import com.guoxiaohei.markdown.utils.JpaBeanUtils;
import com.guoxiaohei.markdown.utils.UuIdUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ArticleService {

    private final Logger log = LoggerFactory.getLogger(ArticleService.class);

    @Autowired
    private ArticleRepository articleRepository;


    @Transactional(rollbackFor = Exception.class)
    public String insertArticle(Article article) {
        String id = UuIdUtil.uuid();
        article.setId(id);
        article.setCreateTime(new Date());
        article.setUpdateTime(article.getCreateTime());
        article.setDeleted(Constant.NOT_DELETED);
        articleRepository.saveAndFlush(article);
        return id;
    }

    @Transactional(rollbackFor = Exception.class)
    public String updateArticle(Article article) {
        Optional<Article> optional = articleRepository.findById(article.getId());
        if (optional.isPresent()) {
            JpaBeanUtils.copyNullProperties(article, optional.get());
            articleRepository.saveAndFlush(optional.get());
        }
        return article.getId();
    }


    public Article findById(String id) {
        return articleRepository.findById(id).orElseGet(Article::new);
    }

    public String deleteById(String id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            article.setDeleted(Constant.IS_DELETED);
            article.setUpdateTime(new Date());
            articleRepository.saveAndFlush(article);
            return article.getId();
        }
        return StringUtils.EMPTY;
    }

    public Page pageArticle(String category, String search, int pageNum, int pageSize) {
        log.info("search category : {}", category);
        log.info("like key : {}", search);
        category = "default".equals(category) ? "" : category;
        Specification<Article> specification = buildQueryCondition(category, search);
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Direction.DESC, "createTime"));
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
            predicates.add(criteriaBuilder.equal(root.get("deleted"), Constant.NOT_DELETED));
            int size = predicates.size();
            return criteriaBuilder.and(predicates.toArray(new Predicate[size]));
        };
    }

}
