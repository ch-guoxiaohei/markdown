package com.guoxiaohei.markdown.dao;

import com.guoxiaohei.markdown.model.projo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleRepository extends JpaRepository<Article, String>,
    JpaSpecificationExecutor {


}
