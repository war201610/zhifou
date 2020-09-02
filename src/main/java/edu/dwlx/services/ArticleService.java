package edu.dwlx.services;

import edu.dwlx.entity.Article;
import edu.dwlx.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    public boolean insertArticle(Article article){
        if(articleMapper.searchArticleByContentAndUid(article) != null){
            return false;
        }
        article.setCreateDate(new Date());
        articleMapper.insertArticle(article);
        Article article1 = articleMapper.searchArticleByContentAndUid(article);
        article1.setComment(article1.getId() + "_article_comment");
        articleMapper.updateArticle(article1);
        articleMapper.createArticleCommentTable(article1.getId());
        return true;
    }

    public void deleteArticle(Article article){
        articleMapper.deleteArticle(article);
    }

    public List<Article> searchArticleByUid(int uid){
        return articleMapper.searchArticleByUid(uid);
    }

    public List<Article> searchArticleByTag(String tag){
        return articleMapper.searchArticleByTag(tag);
    }

    public Article searchArticleById(int id){
        return articleMapper.searchArticleById(id);
    }

    public void updateArticle(Article article){
        articleMapper.updateArticle(article);
    }
}
