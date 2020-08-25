package edu.dwlx.controller.people;

import edu.dwlx.entity.Answer;
import edu.dwlx.entity.Question;
import edu.dwlx.entity.User;
import edu.dwlx.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/zhifou/people")
public class PeopleController {
    @Autowired
    UserMapper userMapper;
    //个人首页
    @RequestMapping("/{uid}")
    public String personalInfo(@PathVariable("uid") String uid, Model model, Authentication authentication) {
        User user = userMapper.searchUserById(uid);
        System.out.println(user);
        String currentUsername = authentication.getName();
        if(currentUsername.equals(user.getName())){
            model.addAttribute("user", user);
            return "/zhifou/people/user.html";
        } else {
            user = userMapper.searchByName(currentUsername);
            return "redirect:" + user.getUid();
        }
    }
    //查看收藏
    @RequestMapping("/{uid}/collections")
    public List<Answer> getCollections(@PathVariable("uid") int uid) {
        return userMapper.searchCollectAnswerByUid(uid);
    }
    //提出的问题
    @RequestMapping("/{id}/asks")
    public List<Question> getAskedQuestions(@PathVariable("uid") int uid) {
        return userMapper.searchAskedQuestionByUid(uid);
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
