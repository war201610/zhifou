package edu.dwlx.controller.answer;

import edu.dwlx.entity.Answer;
import edu.dwlx.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/zhifou/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @RequestMapping("/{table}")
    @ResponseBody
    public List<Answer> getQuestionAnswer(@PathVariable("table") String table) {
        return answerService.searchAnswerByTableName(table);
    }

}
