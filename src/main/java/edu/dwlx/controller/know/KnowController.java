package edu.dwlx.controller.know;

import edu.dwlx.entity.Answer;
import edu.dwlx.entity.Article;
import edu.dwlx.entity.Question;
import edu.dwlx.entity.User;
import edu.dwlx.services.QuestionService;
import edu.dwlx.services.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/zhifou/know")
public class KnowController {

    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;

    //首页显示推荐
    @RequestMapping
    public String toKnow() throws Exception {
        return "/zhifou/know/know.html";
    }
    @RequestMapping("/info")
    @ResponseBody
    //没写推荐
    //根据用户收藏和的内容对应自身或者问题的标签统计对应标签的数量, 选取前三个, 在数据库中查找含有相关标签的内容, 按照浏览数排序
    public List<Question> getRecommendList() {
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

}
