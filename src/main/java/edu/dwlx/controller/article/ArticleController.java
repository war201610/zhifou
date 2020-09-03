package edu.dwlx.controller.article;

import edu.dwlx.entity.Article;
import edu.dwlx.services.ArticleService;
import edu.dwlx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/zhifou/article")
public class ArticleController {

    ArticleService articleService;
    UserService userService;

    @Autowired
    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @RequestMapping("/edit")
    public String test(){
        return "/zhifou/article/ceshi.html";
    }

    @RequestMapping
    @ResponseBody
    public List<Article> show(Model model){
        List<Article> articleList = articleService.getAllArticle();
        model.addAttribute("articleList", articleList);
        return articleList;
    }

    @RequestMapping("/show/info")
//    @ResponseBody
    public String getAllArticle() {
        return "/zhifou/article/showArticle.html";
    }

    @RequestMapping("/showone/{articleId}")
    public String getArticle(Model model, @PathVariable("articleId")int articleId){
        model.addAttribute("article", articleService.searchArticleById(articleId));
        return "/zhifou/article/showOne.html";
    }

    @RequestMapping("/save")
//    @ResponseBody
    public String save(Article article, String[] checkbox){
        String tag = "";
        for(String s: checkbox)
            tag += "#" + s;
        article.setTag(tag);
        if(article.getId() == 0){
            articleService.insertArticle(article);
        }else{
            articleService.updateArticle(article);
        }
        return "/zhifou/know/know.html";
    }
}
