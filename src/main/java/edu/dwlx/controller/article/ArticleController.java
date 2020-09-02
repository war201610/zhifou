package edu.dwlx.controller.article;

import edu.dwlx.entity.Article;
import edu.dwlx.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/zhifou/article")
public class ArticleController {

    ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping("/edit")
    public String test(){
        return "/zhifou/article/ceshi.html";
    }

    @RequestMapping
    public String show(Model model){
        List<Article> articleList = articleService.getAllArticle();
        model.addAttribute("articleList", articleList);
        return "/zhifou/article/showArticle.html";
    }

    @RequestMapping("/show/info")
    @ResponseBody
    public List<Article> getAllArticle() {
        return articleService.getAllArticle();
    }

    @RequestMapping("/show")
    public String getArticle(Model model){
        model.addAttribute("article", articleService.searchArticleById(5));
        return "/zhifou/article/showArticle.html";
    }

    @RequestMapping("/save")
    @ResponseBody
    public int save(Article article, String[] checkbox){
        String tag = "";
        for(String s: checkbox)
            tag += "#" + s;
        article.setTag(tag);
        if(article.getId() == 0){
            articleService.insertArticle(article);
            return 0;
        }else{
            articleService.updateArticle(article);
            return 1;
        }
    }
}
