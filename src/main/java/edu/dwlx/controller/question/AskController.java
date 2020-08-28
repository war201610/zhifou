package edu.dwlx.controller.question;

import edu.dwlx.entity.Question;
import edu.dwlx.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/zhifou/ask")
public class AskController {

    @Autowired
    QuestionService questionService;

    @RequestMapping
    @ResponseBody
    public boolean commitQuestions(Question question) {
        questionService.insertQuestion(question);
        return true;
    }

}
