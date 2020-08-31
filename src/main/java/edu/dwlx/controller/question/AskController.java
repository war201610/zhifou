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
/*
*"uid": 提问者id，
"content": 问题内容，
"introduction": 问题描述，
"tag": 问题标签(数组类型)
*/
    @RequestMapping
    @ResponseBody
    public boolean commitQuestions(int uid, String content, String introduction, String[] tag) {
        Question question = new Question();
        question.setUid(uid);
        question.setContent(content);
        question.setIntroduction(introduction);
        StringBuilder tags = new StringBuilder("");
        for(String t: tag){
            tags.append("#");
            tags.append(t);
        }
        question.setTag(tags.toString());
        questionService.insertQuestion(question);
        return true;
    }

}
