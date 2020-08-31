package edu.dwlx.controller.answer;

import edu.dwlx.entity.Answer;
import edu.dwlx.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/zhifou/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

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
    public boolean getQuestionAnswer(int uid, Integer id, String content, HttpServletRequest request, Model model) {
        model.addAttribute("user", request.getSession().getAttribute("user"));
        Answer answer = new Answer();
        answer.setUid(uid);
        answer.setContent(content);
        System.out.println(uid + " " + content);
        answerService.insertAnswer(answer);
        return true;
    }

}
