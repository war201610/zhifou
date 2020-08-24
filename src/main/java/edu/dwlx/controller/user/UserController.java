package edu.dwlx.controller.user;

import edu.dwlx.entity.Answer;
import edu.dwlx.entity.Collection;
import edu.dwlx.entity.Question;
import edu.dwlx.entity.User;
import edu.dwlx.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    //登陆处理
    @RequestMapping("/zhifou/user/login")
    public String userLogin(String username, String password, Model model, HttpSession session) {
        System.out.println("userLogin");
        User user = userMapper.searchByName(username);
        if(user==null){
            System.out.println("null");
            model.addAttribute("message","用户不存在");
            return "/zhifou/error/error.html";
        }
        String page = "/zhifou/user/login.html";
        if(user.getPassword().equals(password)){
            System.out.println("sucess");
            page = "forward:/zhifou/people/" + user.getUid();
            session.setAttribute("user", user);
            return page;
        }
        else
            model.addAttribute("message", "密码错误");
        return page;
    }
    //注册处理
    @RequestMapping("/zhifou/user/register")
    public String userRegister(String name, String password, Model model) {
        User user = userMapper.searchByName(name);
        if(user != null) {
            model.addAttribute("message", "用户名已存在");
            return "/zhifou/error/error.html";
        } else {
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            user = new User();
            user.setName(name);
            user.setPassword(password);
            user.setRegisterTime(date);
            userMapper.insertUser(user);
            model.addAttribute("message", "注册成功");
            return "/zhifou/user/login.html";
        }
    }
    //个人主页
    @RequestMapping("/zhifou/people/{id}")
    public String personalInfo(@PathVariable("id") String uid, Model model) {
        System.out.println("homepage");
        User user = userMapper.searchUserById(uid);
        model.addAttribute("user", user);
        return "/zhifou/people/personalHomepage.html";
    }
    //查看收藏
    @RequestMapping("/zhifou/people/{id}/collections")
    public List<Answer> getCollection(@PathVariable("id") int id) {
        return userMapper.searchCollectAnswerByUid(id);
    }
    //提出的问题
    @RequestMapping("/zhifou/people/{id}/asks")
    public List<Question> getAskedQuestion(@PathVariable("id") int id) {
        return userMapper.searchAskedQuestionByUid(id);
    }
    //回答
    @RequestMapping("zhifou/people/{id}/answers")
    public List<Question> getAnsweredQuestion(@PathVariable("id") int id) {

        return new ArrayList<Question>();
    }
    //收藏回答和关注问题相同
}
