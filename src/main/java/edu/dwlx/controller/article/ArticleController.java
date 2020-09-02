package edu.dwlx.controller.article;

import edu.dwlx.entity.Article;
import edu.dwlx.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/zhifou/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("/edit")
    public String test(){
        return "/zhifou/article/ceshi.html";
    }

    @RequestMapping("/show")
    public String show(Model model){
        model.addAttribute("article", articleService.searchArticleById(5));
        return "/zhifou/article/showArticle.html";
    }

    @RequestMapping("/save")
    @ResponseBody
    public int save(Article article){
        if(article.getId() == 0){
            articleService.insertArticle(article);
        }else{
            articleService.updateArticle(article);
        }

        return -1;
    }



}
