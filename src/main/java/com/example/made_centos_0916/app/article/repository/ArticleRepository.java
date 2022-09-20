package com.example.made_centos_0916.app.article.repository;

import com.example.made_centos_0916.app.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}