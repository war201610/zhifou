package edu.dwlx.controller.user;

import edu.dwlx.entity.User;
import edu.dwlx.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/zhifou/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    //登陆处理
    @RequestMapping("/login")
    public String userLogin(String username, String password, Model model, HttpSession session) {
        User user = userMapper.searchByName(username);
        if(user==null){
            System.out.println("null");
            model.addAttribute("message","用户不存在");
            return "/zhifou/error/error.html";
        }
        String page = "/zhifou/user/login.html";
        if(user.getPassword().equals(password)){
            page = "forward:/zhifou/people/" + user.getUid();
            session.setAttribute("user", user);
        }
        else
            model.addAttribute("message", "密码错误");
        return page;
    }
    //注册处理
    @RequestMapping("/register")
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
            user.setRegisterDate(date);
            userMapper.insertUser(user);
            model.addAttribute("message", "注册成功");
            return "/zhifou/user/login.html";
        }
    }
}
