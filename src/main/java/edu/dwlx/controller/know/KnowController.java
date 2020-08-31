package edu.dwlx.controller.know;

import edu.dwlx.entity.Question;
import edu.dwlx.entity.User;
import edu.dwlx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/zhifou/know")
public class KnowController {

    @Autowired
    UserService userService;

    //首页显示推荐
    @RequestMapping
    public String toKnow(HttpSession session) throws Exception {
        session.setAttribute("user", "usersjkf");
        return "/zhifou/know/know.html";
    }
    @RequestMapping("/info")
    @ResponseBody
    //没写推荐
    public List<Question> getRecommendList() {
        return new ArrayList<>();
    }
}
