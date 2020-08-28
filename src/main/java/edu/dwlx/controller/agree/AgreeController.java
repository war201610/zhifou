package edu.dwlx.controller.agree;

import edu.dwlx.entity.Answer;
import edu.dwlx.entity.Comment;
import edu.dwlx.entity.Question;
import edu.dwlx.entity.User;
import edu.dwlx.services.AnswerService;
import edu.dwlx.services.CommentService;
import edu.dwlx.services.QuestionService;
import edu.dwlx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/zhifou/agree")
public class AgreeController {

    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerService answerService;
    @Autowired
    CommentService commentService;

//    @RequestMapping
//    @ResponseBody
//    //先不写评论的点赞
//    //kind 点赞的类型, uid 点赞的用户, agreed 被点赞的内容
//    public boolean agree(String kind, int uid, int agreed) {
//        Question question;
//        User user;
//        Answer answer;
////        Comment comment;
//        //被点赞的用户(被点赞内容的用户)
//        int agreedUser;
//        switch (kind) {
//            case "answer":answer = answerService.
//        }
//    }
}
