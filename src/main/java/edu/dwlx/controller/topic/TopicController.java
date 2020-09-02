package edu.dwlx.controller.topic;

import edu.dwlx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/zhifou/topic")
public class TopicController {
    @Autowired
    private UserService userService;
//../topic/topicList.html
    @RequestMapping("/topicList")
    public String toTopic() {
        String page = "/zhifou/topic/topicList.html";
        return page;
    }

//    @RequestMapping("/")
//    public String addressLonLat(Model model) {
//        String page = "/zhifou/user/login.html";
//        if(user.getPassword().equals(password)){
//            UsernamePasswordAuthenticationToken token =
//                    new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword(), user.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(token);
//            page = "redirect:/zhifou/people/" + user.getUid();
//            session.setAttribute("user", user);
//        }
//        else
//            model.addAttribute("message", "密码错误");
//        return page;
//    }
}
