package edu.dwlx.controller.people;

import edu.dwlx.controller.SearchFromList;
import edu.dwlx.entity.Answer;
import edu.dwlx.entity.Article;
import edu.dwlx.entity.Question;
import edu.dwlx.entity.User;
import edu.dwlx.services.QuestionService;
import edu.dwlx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/zhifou/people")
public class PeopleController {
    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;
    //个人首页
    @RequestMapping("/{uid}")
    public String personalHomepage(@PathVariable("uid") int uid) {
        return "/zhifou/people/user.html";
    }
    //查看个人信息
    @RequestMapping("/{uid}/info")
    @ResponseBody
    public User personalInfo(@PathVariable("uid") int uid) {
        return userService.searchUserById(uid);
    }
    //查看收藏
    @RequestMapping("/{uid}/collections")
    @ResponseBody
    public Map<String, Object> getCollections(@PathVariable("uid") int uid) {
        Map<String, Object> collection = new HashMap<>();
        List<Answer> answers = userService.searchCollectAnswerByUid(uid);
        List<Article> articles = userService.searchCollectArticleByUid(uid);
        collection.put("answers", answers);
        collection.put("articles", articles);
        return collection;
    }
    //提出的问题
    @RequestMapping("/{uid}/asks")
    @ResponseBody
    public List<Question> getAskedQuestions(@PathVariable("uid") int uid) {
        return questionService.searchQuestionByUid(uid);
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
        return questionService.searchQuestionByUid(uid);
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
    //编辑个人信息
    @RequestMapping("/{uid}/edit")
    @ResponseBody
    public boolean editPersonalInfo(@PathVariable("uid") int uid, User user) {
        User user1 = userService.searchUserById(user.getUid());
        user.setUid(user1.getUid());
        user.setName(user1.getName());
        user.setPassword(user1.getPassword());
        user.setRegisterDate(user1.getRegisterDate());
        user.setFollowing(user1.getFollowing());
        user.setFollower(user1.getFollower());
        user.setCollectArticle(user1.getCollectArticle());
        user.setCollectAnswer(user1.getCollectAnswer());
        user.setAnswer(user1.getAnswer());
        user.setLikeCount(user1.getLikeCount());
        userService.updateUser(user);
        return true;
    }
    //取消关注用户
    @RequestMapping("/{self}/following/{subscribed}")
    @ResponseBody
    public boolean unsubscribe(@PathVariable("self") int self, @PathVariable("subscribed") int subscribed) {
        //被关注的人删除粉丝
        userService.deleteFollower(subscribed, self);
        //关注的人删除关注
        userService.deleteFollowing(self, subscribed);
        return true;
    }
    //关注用户
    @RequestMapping("/{self}/followers/{subscribed}")
    @ResponseBody
    public boolean subscribe(@PathVariable("self") int self, @PathVariable("subscribed") int subscribed) {
        List<User> userList = userService.searchFollowerByUid(subscribed);
        if(SearchFromList.searchUser(self, userList)==null){
            //被关注的人添加粉丝
            userService.insertFollower(subscribed, self);
            //关注的人添加关注者
            userService.insertFollowing(self, subscribed);
            return true;
        }
        return false;
    }
}
