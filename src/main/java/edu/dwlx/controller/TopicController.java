package edu.dwlx.controller;

import edu.dwlx.entity.Question;
import edu.dwlx.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class TopicController {
    private final QuestionService questionService;

    @Autowired
    public TopicController(QuestionService q) {
        this.questionService = q;
    }

    @RequestMapping("/zhifou/find")
    public String toTopicList() {
        return "/zhifou/topicHtml/topicList.html";
    }

    @RequestMapping("/zhifou/topic/{kind}")
    public String topic(@PathVariable("kind") String kind,
                        HttpServletResponse response) throws Exception {
        return "/zhifou/topicHtml/topicQuestions.html";
    }
    @RequestMapping("/zhifou/topic/{kind}/info")
    @ResponseBody
    public List<Question> topicData(@PathVariable("kind") String kind,
                        HttpServletResponse response) throws Exception {
        List<Question> questionList = null;
        switch (kind) {
            case "movie":
                questionList = questionService.searchQuestionByTag("电影");
                break;
            case "video":
                questionList = questionService.searchQuestionByTag("视频");
                break;
            case "device":
                questionList = questionService.searchQuestionByTag("数码");
                break;
            case "fashion":
                questionList = questionService.searchQuestionByTag("时尚");
                break;
            case "sports":
                questionList = questionService.searchQuestionByTag("运动");
                break;
            case "science":
                questionList = questionService.searchQuestionByTag("科学");
                break;
            default:
                response.sendError(403, "参数错误");
        }
        return questionList;
    }

}
