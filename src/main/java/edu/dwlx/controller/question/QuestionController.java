package edu.dwlx.controller.question;

import edu.dwlx.entity.Question;
import edu.dwlx.services.QuestionService;
import edu.dwlx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/zhifou/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    //问题页
    @RequestMapping("/{id}")
    public String questionPage(@PathVariable("id") int id) {
        return "/zhifou/question/answer.html";
    }
    //返回问题数据
    @RequestMapping("/{id}/info")
    @ResponseBody
    public Question questionDate(@PathVariable("id") int id) {
        return questionService.searchQuestionById(id);
    }
    //搜索问题(未完成)
    @RequestMapping("/search")
    @ResponseBody
    public List<Question> searchQuestionByContent(String content, HttpServletRequest request) {
//这里想按照空格分隔关键词, 根据不同的关键词查找所有的问题, 再合并到一起, 这样就需要剔除所有重复的问题
//        String[] keyword = content.split(" ");
//        int size = keyword.length;
//        List<Question> result = new ArrayList<>();
//        for(int i = 0; i < size; i++) {
//
//        }
        //没指定跳转的页面
        request.getRequestDispatcher("/zhifou/question/answer.html");
        return new ArrayList<>();
//        return questionService.searchQuestionByContent(content);
    }
}
