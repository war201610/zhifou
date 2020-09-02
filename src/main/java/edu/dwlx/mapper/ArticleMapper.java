package edu.dwlx.mapper;

import edu.dwlx.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleMapper {

    void insertArticle(Article article);

    void createArticleCommentTable(@Param("articleId") Integer articleId);

    void deleteArticle(Article article);

    void updateArticle(Article article);

    List<Article> searchArticleByUid(@Param("uid") Integer uid);

    List<Article> searchArticleByTag(@Param("tag")String tag);

    Article searchArticleById(@Param("id")int id);

    Article searchArticleByContentAndUid(Article article);
}
