package webtech.gr14.repository.article;

import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.article.LocationArticle;

public interface LocationArticleR  extends JpaRepository<LocationArticle, Integer>{

}