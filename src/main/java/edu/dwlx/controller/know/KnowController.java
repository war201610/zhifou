package edu.dwlx.controller.know;

import edu.dwlx.entity.Question;
import edu.dwlx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/zhifou/know")
public class KnowController {

    @Autowired
    UserService userService;

    //首页显示推荐
    @RequestMapping
    @ResponseBody
    public List<Question> toKnow(HttpServletResponse response) throws Exception {
        response.sendRedirect("/zhifou/know/know.html");
        return new ArrayList<>();
    }
}
