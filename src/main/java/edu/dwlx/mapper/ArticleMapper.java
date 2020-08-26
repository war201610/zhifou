package edu.dwlx.mapper;

import edu.dwlx.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {

    void insertArticle(Article article);

    void createArticleCommentTable(@Param("articleId") Integer articleId);

    void deleteArticle(Article article);

    void updateArticle(Article article);

    List<Article> searchArticleByUid(@Param("uid") Integer uid);

    List<Article> searchArticleByTag(@Param("tag")String tag);

    Article searchArticleByContentAndUid(Article article);
}
