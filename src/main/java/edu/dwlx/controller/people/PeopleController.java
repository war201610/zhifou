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
    public String personalInfo(@PathVariable("uid") String uid, Model model) {
        User user = userService.searchUserById(new Integer(uid));
        model.addAttribute("user", user);
        return "/zhifou/people/user.html";
    }
    //查看收藏
    @RequestMapping("/{uid}/collections")
    public List<Answer> getCollections(@PathVariable("uid") int uid) {
        return userService.searchCollectAnswerByUid(uid);
    }
    //提出的问题
    @RequestMapping("/{id}/asks")
    public List<Question> getAskedQuestions(@PathVariable("uid") int uid) {
        return userService.searchQuestionByUid(uid);
    }
    //回答
    @RequestMapping("/{uid}/answers")
    public List<Answer> getAnsweredQuestions(@PathVariable("uid") int uid) {
        Answer a1 = new Answer();
        Answer a2 = new Answer();
        List<Answer> list = new ArrayList<>();
        list.add(a1);
        list.add(a2);
        return list;
    }
    //收藏回答和关注问题相同
    @RequestMapping("/{uid}/questions")
    public List<Question> getSubscribeQuestions(@PathVariable("uid") int uid) {
        return new ArrayList<Question>();
    }
    //关注的用户
    @RequestMapping("/{uid}/following")
    public List<User> getSubscribeUsers(@PathVariable("uid") int uid) {
        return new ArrayList<User>();
    }
    //粉丝
    @RequestMapping("/{uid}/followers")
    public List<User> getFollowers(@PathVariable("uid") int uid) {
        return new ArrayList<User>();
    }
}
