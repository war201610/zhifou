package edu.dwlx.controller.question;

import edu.dwlx.controller.SearchFromList;
import edu.dwlx.entity.Answer;
import edu.dwlx.entity.Question;
import edu.dwlx.services.AnswerService;
import edu.dwlx.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/zhifou/question")
public class QuestionController {

    QuestionService questionService;
    AnswerService answerService;

    @Autowired//直接在字段上面添加@Autowired是不推荐的
    public QuestionController(QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }

    //问题页
//    @RequestMapping("/{id}")
//    public String questionPage(@PathVariable("id") int id, HttpServletRequest request, Model model) {
//        model.addAttribute("user", request.getSession().getAttribute("user"));
//        return "/zhifou/question/answer.html";
//    }
    //回答页
//    @RequestMapping("/{questionId}/answer/{answerId}")
    @RequestMapping("/b/a")
    public String answerPage() {
        return "/zhifou/question/answer.html";
    }

    //返回问题数据
    @RequestMapping("/{id}/info")
    @ResponseBody
    public Map<String, Object> questionData(@PathVariable("id") int id) {
        Map<String, Object> map = new HashMap<>();
        Question question = questionService.searchQuestionById(id);
        if(question == null)
            return null;
        map.put("question", question);
        map.put("answerCount", answerService.getAnswerCount(question.getAnswer()));
        return map;
    }
    //搜索问题
    @RequestMapping("/search")
    @ResponseBody
    public List<Question> searchQuestionByContent(String content) {
        String[] keyword = content.split(" ");
        List<Question> questionList = new ArrayList<>();
        for(String k : keyword) {
            List<Question> temp = questionService.searchQuestionByContent(k);
            for(Question q : temp) {
                if(SearchFromList.searchQuestion(q.getId(), questionList)==null)
                    questionList.add(q);
            }
        }
        return questionList;
    }

    @RequestMapping("/{questionId}/answer/{answerId}/info")
    @ResponseBody
    public Map<String, Object> getCollectAnswer(@PathVariable("questionId")int questionId, @PathVariable("answerId")int answerId){
        Map<String, Object> map = new HashMap<>();
        Question question = questionService.searchQuestionById(questionId);
        map.put("question", question);
        List<Answer> list = answerService.searchAnswerByQuestionId(questionId);
        Answer answer1 = list.get(answerId-1);
        Answer answer2 = list.get(0);
        list.set(0, answer1);
        list.set(answerId-1, answer2);
        map.put("list", list);
        return map;
    }
}
