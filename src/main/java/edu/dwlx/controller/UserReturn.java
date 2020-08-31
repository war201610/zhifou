package edu.dwlx.controller;

import edu.dwlx.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserReturn {
    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(HttpSession session) {
        return (User)session.getAttribute("user");
    }
}
