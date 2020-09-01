package edu.dwlx.controller.question;

import edu.dwlx.entity.Answer;
import edu.dwlx.entity.Question;
import edu.dwlx.services.AnswerService;
import edu.dwlx.services.QuestionService;
import edu.dwlx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/zhifou/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerService answerService;

    //问题页
    @RequestMapping("/{id}")
    public String questionPage(@PathVariable("id") int id, HttpServletRequest request, Model model) {
        model.addAttribute("user", request.getSession().getAttribute("user"));
        return "/zhifou/question/answer.html";
    }
    //返回问题数据
    @RequestMapping("/{id}/info")
    @ResponseBody
    public Map questionDate(@PathVariable("id") int id) {
        Map<String, Object> map = new HashMap<>();
        Question question = questionService.searchQuestionById(id);
        map.put("question", question);
        map.put("answerCount", answerService.getAnswerCount(question.getAnswer()));
        return map;
    }
    //搜索问题(未完成)
    @RequestMapping("/search")
    @ResponseBody
    public List<Question> searchQuestionByContent(String content) {
        String[] keyword = content.split(" ");
        List<Question> questionList = new ArrayList<>();
        for(String k : keyword) {
            List<Question> temp = questionService.searchQuestionByContent(k);
            for(Question q : temp) {
                if(!searchQuestion(q.getId(), questionList))
                    questionList.add(q);
            }
        }
        return questionList;
    }
    public Boolean searchQuestion(int id, List<Question> questionList) {
        int size = questionList.size();
        int left = 0;
        int right = size - 1;
        int mid;
        while(left <= right) {
            mid = left + (right - left)/2;
            if(questionList.get(mid).getId() == id)
                return true;
            else if(questionList.get(mid).getId() > id)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return false;
    }

    @RequestMapping("/{questionId}/answer/{answerId}")
    @ResponseBody
    public Map<String, Object> getCollectAnswer(@PathVariable("questionId")int questionId, @PathVariable("answerId")int answerId){
        Map<String, Object> map = new HashMap<String, Object>();
        Question question = questionService.searchQuestionById(questionId);
        map.put("question", question);
        List<Answer> list = answerService.searchAnswerByQuestionId(questionId);
        Answer answer1 = list.get(answerId);
        Answer answer2 = list.get(0);
        list.set(0, answer1);
        list.set(answerId, answer2);
        map.put("list", list);
        return map;
    }
}
