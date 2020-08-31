package edu.dwlx.controller.answer;

import edu.dwlx.entity.Answer;
import edu.dwlx.entity.Question;
import edu.dwlx.services.AnswerService;
import edu.dwlx.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/zhifou/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;
    QuestionService questionService;

    //获得回答
    @RequestMapping("/get/{table}")
    @ResponseBody
    public List<Answer> getQuestionAnswer(@PathVariable("table") String table, HttpServletRequest request, Model model) {
        model.addAttribute("user", request.getSession().getAttribute("user"));
        return answerService.searchAnswerByTableName(table);
    }
    //添加回答
    @RequestMapping("/put")
    @ResponseBody
    public boolean getQuestionAnswer(int uid, int id, String content, HttpServletRequest request, Model model) {
        model.addAttribute("user", request.getSession().getAttribute("user"));
        Answer answer = new Answer();
        answer.setUid(uid);
        answer.setId(id);
        answer.setContent(content);
        answerService.insertAnswer(answer);
        return true;
    }

    @RequestMapping("/collectAnswer")
    @ResponseBody
    public Map<String, Object> getCollectAnswer(Answer answer){
        int questionId = answer.getQuestionId();
        int answerId = answer.getId();

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
