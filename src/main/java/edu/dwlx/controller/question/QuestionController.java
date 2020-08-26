package edu.dwlx.controller.question;

import edu.dwlx.entity.Question;
import edu.dwlx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/zhifou/question")
public class QuestionController {

    @Autowired
    UserService userService;

    //问题页
    @RequestMapping("/{id}")
    public String questionPage(@PathVariable("id") int id) {
        return "/zhifou/question/answer.html";
    }
    //返回问题数据
    @RequestMapping("/{id}/info")
    @ResponseBody
    public Question questionDate(@PathVariable("id") int id) {
        return  new Question();
    }

}
