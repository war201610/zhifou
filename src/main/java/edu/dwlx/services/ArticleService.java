package edu.dwlx.services;

import edu.dwlx.entity.Article;
import edu.dwlx.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    public void insertArticle(Article article){
        articleMapper.insertArticle(article);
        Article article1 = articleMapper.searchArticleByContentAndUid(article);
        article1.setComment(article1.getId() + "_article_comment");
        articleMapper.updateArticle(article1);
        articleMapper.createArticleCommentTable(article1.getId());
    }

    public void deleteArticle(Article article){
        articleMapper.deleteArticle(article);
        articleMapper.deleteArticleCommentTable(article.getId());
    }

    public List<Article> searchArticleByUid(int uid){
        return articleMapper.searchArticleByUid(uid);
    }

    public List<Article> searchArticleByTag(String tag){
        return articleMapper.searchArticleByTag(tag);
    }
}
