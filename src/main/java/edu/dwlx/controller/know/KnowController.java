package edu.dwlx.controller.know;

import edu.dwlx.entity.Answer;
import edu.dwlx.entity.Article;
import edu.dwlx.entity.Question;
import edu.dwlx.entity.User;
import edu.dwlx.services.AnswerService;
import edu.dwlx.services.QuestionService;
import edu.dwlx.services.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QuerydslUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.random;

@Controller
@RequestMapping("/zhifou/know")
public class KnowController {

    UserService userService;
    QuestionService questionService;
    AnswerService answerService;

    @Autowired
    public KnowController(UserService userService, QuestionService questionService,
                          AnswerService answerService) {
        this.userService = userService;
        this.answerService = answerService;
        this.questionService = questionService;
    }

    //首页显示推荐
    @RequestMapping
    public String toKnow() throws Exception {
        return "/zhifou/know/know.html";
    }
    @RequestMapping("/info")
    @ResponseBody
    //没写推荐
    //根据用户收藏和的内容对应自身或者问题的标签统计对应标签的数量, 选取前三个, 在数据库中查找含有相关标签的内容, 按照浏览数排序
    public Map<String , Object> getRecommendList() {
//        User user = userService.searchUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
//        List<Answer> collectAnswerList = userService.searchCollectAnswerByUid(user.getUid());
//        List<Integer> countList = new ArrayList<>();//长度为6, 从0到5对应时尚, 数码, 视频, 电影, 科学, 运动
//        for(int i = 1 ; i <= 6; i++)
//            countList.add(0);
//        for(Answer a : collectAnswerList) {
//            Question collectQuestion = questionService.searchQuestionById(a.getQuestionId());
//            String[] tags = collectQuestion.getTag().split("#");//第一个内容tags[0]为空
//            for(String tag : tags) {
//                switch (tag) {
//                    case "" : {
//                        break;
//                    }
//                }
//            }
//        }
        List<Question> questionList = questionService.getAllQuestion();
        int size = questionList.size();
        Map<String, Object> map = new HashMap<>();
        if(size <= 10){
            map.put("questionList", questionList);
            List<Integer> sizeList = new ArrayList<>();
//            System.out.println(questionList.toString());
            for(Question q : questionList)
                sizeList.add(answerService.getAnswerCount(q.getAnswer()));
            map.put("answerCountList", sizeList);
            return map;
        }
        List<Question> resultList = new ArrayList<>();
        int limit = (size - size%10)/10;
        double compare = 1.0/limit;
        for(Question q : questionList) {
            double temp = Math.random();
            if(temp < compare)
                resultList.add(q);
        }
        map.put("questionList", resultList);
        List<Integer> sizeList = new ArrayList<>();
        for(Question q : resultList)
            sizeList.add(answerService.getAnswerCount(q.getAnswer()));
        map.put("answerCountList", sizeList);
        return map;

    }
}
