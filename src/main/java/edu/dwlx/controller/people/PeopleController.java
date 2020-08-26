package edu.dwlx.controller.people;

import edu.dwlx.entity.Answer;
import edu.dwlx.entity.Question;
import edu.dwlx.entity.User;
import edu.dwlx.mapper.UserMapper;
import edu.dwlx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/zhifou/people")
public class PeopleController {
    @Autowired
    UserService userService;
    //个人首页
    @RequestMapping("/{uid}")
    public String personalHomepage(@PathVariable("uid") int uid, Model model) {
        User user = userService.searchUserById(uid);
        model.addAttribute("user", user);
        return "/zhifou/people/user.html";
    }
    //查看个人信息
    @RequestMapping("/{uid}/info")
    @ResponseBody
    public User personalInfo(@PathVariable("uid") int uid) {
        System.out.println(userService.searchUserById(uid));
        return userService.searchUserById(uid);
    }
    //查看收藏
    @RequestMapping("/{uid}/collections")
    @ResponseBody
    public List<Answer> getCollections(@PathVariable("uid") int uid) {
        return userService.searchCollectAnswerByUid(uid);
    }
    //提出的问题
    @RequestMapping("/{id}/asks")
    @ResponseBody
    public List<Question> getAskedQuestions(@PathVariable("uid") int uid) {
        return userService.searchQuestionByUid(uid);
    }
    //回答
    @RequestMapping("/{uid}/answers")
    @ResponseBody
    public List<Answer> getAnsweredQuestions(@PathVariable("uid") int uid) {
        return userService.searchAnswerByUid(uid);
    }
    //关注的问题
    @RequestMapping("/{uid}/questions")
    @ResponseBody
    public List<Question> getSubscribeQuestions(@PathVariable("uid") int uid) {
        return userService.searchQuestionByUid(uid);
    }
    //关注的用户
    @RequestMapping("/{uid}/following")
    @ResponseBody
    public List<User> getSubscribeUsers(@PathVariable("uid") int uid) {
        return userService.searchFollowingByUid(uid);
    }
    //粉丝
    @RequestMapping("/{uid}/followers")
    @ResponseBody
    public List<User> getFollowers(@PathVariable("uid") int uid) {
        return userService.searchFollowerByUid(uid);
    }
}
